package com.geovis.manager.bs.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbCheckPlanDTO;
import com.geovis.manager.bs.dto.TbCheckPlanQueryDTO;
import com.geovis.manager.bs.dto.TbCheckPlanSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbCheckPlan;
import com.geovis.manager.bs.entity.TbCheckPlanRelated;
import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.bs.service.ITbCheckPlanRelatedService;
import com.geovis.manager.bs.service.ITbCheckPlanService;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 检查计划 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/bs/tbCheckPlan")
@RequiredArgsConstructor
@Api(value = "检查计划接口", tags = "检查计划接口")
@Slf4j
@Validated
public class TbCheckPlanController extends BaseController<ITbCheckPlanService> {

    private final ITbCheckPlanRelatedService checkPlanRelatedService;

    private final ITbEnterpriseService enterpriseService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbCheckPlanDTO>> getPage(@RequestBody @Validated PageParam<TbCheckPlanQueryDTO> pageParam) {
        IPage<TbCheckPlan> page = pageParam.buildPage();
        TbCheckPlanQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbCheckPlan> wrapper = Wrappers.lambdaQuery(TbCheckPlan.class).orderByDesc(TbCheckPlan::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbCheckPlan::getType, queryDTO.getType());
            if (ObjectUtil.isNotEmpty(queryDTO.getCheckStartDate()) && ObjectUtil.isEmpty(queryDTO.getCheckEndDate())) {
                wrapper.and(wp -> wp.ge(TbCheckPlan::getCheckStartDate, queryDTO.getCheckStartDate()).le(TbCheckPlan::getCheckEndDate, queryDTO.getCheckStartDate()));
            }
            if (ObjectUtil.isNotEmpty(queryDTO.getCheckEndDate()) && ObjectUtil.isEmpty(queryDTO.getCheckStartDate())) {
                wrapper.and(wp -> wp.ge(TbCheckPlan::getCheckStartDate, queryDTO.getCheckEndDate()).le(TbCheckPlan::getCheckEndDate, queryDTO.getCheckEndDate()));
            }
            if (ObjectUtil.isNotEmpty(queryDTO.getCheckStartDate()) && ObjectUtil.isNotEmpty(queryDTO.getCheckEndDate())) {
                wrapper.and(wp -> wp.ge(TbCheckPlan::getCheckStartDate, queryDTO.getCheckStartDate()).le(TbCheckPlan::getCheckEndDate, queryDTO.getCheckStartDate())
                        .or()
                        .ge(TbCheckPlan::getCheckStartDate, queryDTO.getCheckEndDate()).le(TbCheckPlan::getCheckEndDate, queryDTO.getCheckEndDate()));
            }
        }
        baseService.page(page, wrapper);
        List<TbCheckPlanDTO> dtoList = CollUtil.list(false);
        Set<String> planIdSet = page.getRecords().stream().map(TbCheckPlan::getId).collect(Collectors.toSet());
        Map<String, List<String>> planListMap = IterUtil.toListMap(checkPlanRelatedService.list(Wrappers.lambdaQuery(TbCheckPlanRelated.class).in(TbCheckPlanRelated::getCheckPlanId, planIdSet)), TbCheckPlanRelated::getCheckPlanId, TbCheckPlanRelated::getEnterpriseId);
        Map<String, TbEnterprise> enterpriseMap = IterUtil.toMap(enterpriseService.list(), TbEnterprise::getId);
        Map<String, List<TbEnterprise>> map = MapUtil.newHashMap();
        planListMap.forEach((planId, enterpriseIdList) -> {
            List<TbEnterprise> list = CollUtil.list(false);
            enterpriseIdList.forEach(enterpriseId -> {
                list.add(enterpriseMap.get(enterpriseId));
            });
        });
        page.getRecords().forEach(entity -> {
            TbCheckPlanDTO tbCheckPlanDTO = BeanUtil.copyProperties(entity, TbCheckPlanDTO.class);
            tbCheckPlanDTO.setEnterpriseList(map.get(tbCheckPlanDTO.getId()));
            dtoList.add(tbCheckPlanDTO);
        });

        IPage<TbCheckPlanDTO> pageDto = pageParam.buildPage();
        pageDto.setRecords(dtoList).setTotal(page.getTotal());
        return Result.ok(new PageResult<TbCheckPlanDTO>(pageDto));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbCheckPlan> saveOrUpdate(@RequestBody @Validated TbCheckPlanSaveOrUpdateDTO dto) {
        TbCheckPlan entity = BeanUtil.copyProperties(dto, TbCheckPlan.class);
        baseService.saveOrUpdate(entity);
        checkPlanRelatedService.remove(Wrappers.lambdaQuery(TbCheckPlanRelated.class).eq(TbCheckPlanRelated::getCheckPlanId, entity.getId()));
        if (CollUtil.isNotEmpty(dto.getEnterpriseIdList())) {
            dto.getEnterpriseIdList().forEach(enterpriseId -> {
                TbCheckPlanRelated related = new TbCheckPlanRelated();
                related.setCheckPlanId(entity.getId())
                        .setEnterpriseId(enterpriseId);
                checkPlanRelatedService.save(related);
            });
        }
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }

}
