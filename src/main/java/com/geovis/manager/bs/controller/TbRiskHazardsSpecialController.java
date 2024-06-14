package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.RiskHazardsSpecialConstant;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.bs.entity.TbRiskHazardsSpecial;
import com.geovis.manager.bs.service.ITbRiskHazardsSpecialService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 风险隐患管理_特殊作业报备 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/bs/tbRiskHazardsSpecial")
@RequiredArgsConstructor
@Api(value = "风险隐患管理_特殊作业报备接口", tags = "风险隐患管理_特殊作业报备接口")
@Slf4j
@Validated
public class TbRiskHazardsSpecialController extends BaseController<ITbRiskHazardsSpecialService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbRiskHazardsSpecial>> getPage(@RequestBody @Validated PageParam<TbRiskHazardsSpecialQueryDTO> pageParam) {
        IPage<TbRiskHazardsSpecial> page = pageParam.buildPage();
        TbRiskHazardsSpecialQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbRiskHazardsSpecial> wrapper = Wrappers.lambdaQuery(TbRiskHazardsSpecial.class).orderByDesc(TbRiskHazardsSpecial::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getTaskType()), TbRiskHazardsSpecial::getTaskType, queryDTO.getTaskType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getTaskStatus()), TbRiskHazardsSpecial::getTaskStatus, queryDTO.getTaskStatus())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbRiskHazardsSpecial::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbRiskHazardsSpecial>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbRiskHazardsSpecial> saveOrUpdate(@RequestBody @Validated TbRiskHazardsSpecialSaveOrUpdateDTO dto) {
        TbRiskHazardsSpecial entity = BeanUtil.copyProperties(dto, TbRiskHazardsSpecial.class);
        if (StrUtil.isEmpty(entity.getTaskStatus())) {
            entity.setTaskStatus(RiskHazardsSpecialConstant.TASK_STATUS_BE_DING);
        }
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }

    @ApiOperation("变更状态")
    @ApiOperationSupport(order = 3)
    @PostMapping("/changeTaskStatus")
    public Result<TbRiskHazardsSpecial> changeTaskStatus(@RequestBody @Validated TbRiskHazardsSpecialChangeStatusDTO dto) {
        TbRiskHazardsSpecial entity = baseService.getById(dto.getId());
        entity.setTaskStatus(dto.getTaskStatus());
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }


    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 4)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }


    @ApiOperation("根据ID查看详情")
    @ApiOperationSupport(order = 5)
    @PostMapping("/getById/{id}")
    public Result<TbRiskHazardsSpecial> getById(@PathVariable("id") String id) {

        return Result.ok(baseService.getById(id));
    }


    @ApiOperation("列表")
    @ApiOperationSupport(order = 6)
    @PostMapping("/list")
    public Result<List<TbRiskHazardsSpecial>> list() {

        return Result.ok(baseService.list());
    }


    @ApiOperation("按类别统计")
    @ApiOperationSupport(order = 6)
    @PostMapping("/statistic")
    public Result<List<TbRiskHazardsSpecialStatisticDTO>> statistic() {

        List<TbRiskHazardsSpecialStatisticDTO> list = new ArrayList<>();
        for(int i=0;i<8;i++){
            TbRiskHazardsSpecialStatisticDTO tbRiskHazardsSpecialStatisticDTO = new TbRiskHazardsSpecialStatisticDTO();
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<TbRiskHazardsSpecial>().eq(TbRiskHazardsSpecial::getTaskType,String.valueOf(i+1));
            long counti = baseService.count(lambdaQueryWrapper);
            tbRiskHazardsSpecialStatisticDTO.setTaskType(String.valueOf(i+1));
            tbRiskHazardsSpecialStatisticDTO.setNum(String.valueOf(counti));
            list.add(tbRiskHazardsSpecialStatisticDTO);
        }

        return Result.ok(list);
    }


    @ApiOperation("按企业统计前5")
    @ApiOperationSupport(order = 6)
    @PostMapping("/statisticTop5")
    public Result<List<TbRiskHazardsSpecialStatisticDTO>> statisticTop5() {


        return Result.ok(baseService.statisticTop5());
    }



}
