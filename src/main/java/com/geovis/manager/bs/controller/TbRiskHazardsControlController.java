package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbRiskHazardsControlQueryDTO;
import com.geovis.manager.bs.dto.TbRiskHazardsControlRemindDTO;
import com.geovis.manager.bs.dto.TbRiskHazardsControlSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbRiskHazardsControl;
import com.geovis.manager.bs.service.ITbRiskHazardsControlService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 风险隐患管理_风险管控 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/bs/tbRiskHazardsControl")
@RequiredArgsConstructor
@Api(value = "风险隐患管理_风险管控接口", tags = "风险隐患管理_风险管控接口")
@Slf4j
@Validated
public class TbRiskHazardsControlController extends BaseController<ITbRiskHazardsControlService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbRiskHazardsControl>> getPage(@RequestBody @Validated PageParam<TbRiskHazardsControlQueryDTO> pageParam) {
        IPage<TbRiskHazardsControl> page = pageParam.buildPage();
        TbRiskHazardsControlQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbRiskHazardsControl> wrapper = Wrappers.lambdaQuery(TbRiskHazardsControl.class).orderByDesc(TbRiskHazardsControl::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getDangerStatus()), TbRiskHazardsControl::getDangerStatus, queryDTO.getDangerStatus())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbRiskHazardsControl::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbRiskHazardsControl>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbRiskHazardsControl> saveOrUpdate(@RequestBody @Validated TbRiskHazardsControlSaveOrUpdateDTO dto) {
        TbRiskHazardsControl entity = BeanUtil.copyProperties(dto, TbRiskHazardsControl.class);
        if (StrUtil.isEmpty(entity.getHasWaring())) {
            entity.setHasWaring(CommonConstants.NO);
        }
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }

    @ApiOperation("预警提醒")
    @ApiOperationSupport(order = 3)
    @PostMapping("/waringRemind")
    public Result<TbRiskHazardsControl> waringRemind(@RequestBody @Validated TbRiskHazardsControlRemindDTO dto) {
        TbRiskHazardsControl entity = baseService.getById(dto.getId());
        entity.setHasWaring(CommonConstants.YES)
                .setWaringContent(dto.getWaringContent());
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
    public Result<TbRiskHazardsControl> getById(@PathVariable("id") String id) {

        return Result.ok(baseService.getById(id));
    }



    @ApiOperation("风险管控总数量")
    @ApiOperationSupport(order = 6)
    @PostMapping("/count")
    public Result<?> count() {
        return Result.ok(baseService.count());
    }
}
