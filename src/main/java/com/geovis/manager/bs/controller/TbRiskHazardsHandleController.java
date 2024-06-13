package com.geovis.manager.bs.controller;


import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbRiskHazardsHandle;
import com.geovis.manager.bs.service.ITbRiskHazardsHandleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患整改 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@RestController
@RequestMapping("/bs/tbRiskHazardsHandle")
@RequiredArgsConstructor
@Api(value = "风险隐患管理_隐患整改接口", tags = "风险隐患管理_隐患整改接口")
@Slf4j
@Validated
public class TbRiskHazardsHandleController extends BaseController<ITbRiskHazardsHandleService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbRiskHazardsDTO>> getPage(@RequestBody @Validated PageParam<TbRiskHazardsQueryDTO> pageParam) {
        return Result.ok(baseService.getRiskHazardsPage(pageParam));
    }

    @ApiOperation("查询详情")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getById/{id}")
    public Result<TbRiskHazardsDTO> getRiskHazardsById(@PathVariable("id") String id) {
        return Result.ok(baseService.getRiskHazardsById(id));
    }

    @ApiOperation("移动端-隐患上报")
    @ApiOperationSupport(order = 3)
    @PostMapping("/appSave")
    public Result<TbRiskHazardsHandle> appSave(@RequestBody @Validated TbRiskHazardsAppSaveDTO dto) {
        return Result.ok(baseService.appSave(dto));
    }

    @ApiOperation("企业端-隐患整改")
    @ApiOperationSupport(order = 4)
    @PostMapping("/handle")
    public Result<TbRiskHazardsHandle> handle(@RequestBody @Validated TbRiskHazardsHandleDTO dto) {
        return Result.ok(baseService.handle(dto));
    }

    @ApiOperation("园区端-隐患督办")
    @ApiOperationSupport(order = 5)
    @PostMapping("/supervision")
    public Result<TbRiskHazardsHandle> supervision(@RequestBody @Validated TbRiskHazardsSupervisionDTO dto) {
        return Result.ok(baseService.supervision(dto));
    }

    @ApiOperation("园区端-隐患新增并督办")
    @ApiOperationSupport(order = 6)
    @PostMapping("/parkSaveAndSupervision")
    public Result<TbRiskHazardsHandle> parkSaveAndSupervision(@RequestBody @Validated TbRiskHazardsAddSupervisionDTO dto) {
        return Result.ok(baseService.parkSaveAndSupervision(dto));
    }

    @ApiOperation("园区端-隐患复查")
    @ApiOperationSupport(order = 7)
    @PostMapping("/check")
    public Result<TbRiskHazardsHandle> check(@RequestBody @Validated TbRiskHazardsCheckDTO dto) {
        return Result.ok(baseService.check(dto));
    }

    @ApiOperation("月度隐患统计")
    @ApiOperationSupport(order = 8)
    @PostMapping("/yearMonthStatistic")
    public Result<List<StatisticDTO>> yearMonthStatistic(@RequestBody @Validated TbRiskHazardsStatisticParamDTO dto) {
        return Result.ok(baseService.yearMonthStatistic(dto));
    }

    @ApiOperation("行业重大隐患统计")
    @ApiOperationSupport(order = 9)
    @PostMapping("/industryStatistic")
    public Result<List<StatisticDTO>> industryStatistic(@RequestBody @Validated TbRiskHazardsStatisticParamDTO dto) {
        return Result.ok(baseService.industryStatistic(dto));
    }

    @ApiOperation("隐患级别统计")
    @ApiOperationSupport(order = 10)
    @PostMapping("/levelStatistic")
    public Result<List<StatisticDTO>> levelStatistic(@RequestBody @Validated TbRiskHazardsStatisticParamDTO dto) {
        return Result.ok(baseService.levelStatistic(dto));
    }

    @ApiOperation("隐患整改状态统计")
    @ApiOperationSupport(order = 11)
    @PostMapping("/statusStatistic")
    public Result<List<StatisticDTO>> statusStatistic(@RequestBody @Validated TbRiskHazardsStatisticParamDTO dto) {
        return Result.ok(baseService.statusStatistic(dto));
    }

    @ApiOperation("隐患企业所处网格统计")
    @ApiOperationSupport(order = 12)
    @PostMapping("/gridStatistic")
    public Result<List<StatisticDTO>> gridStatistic(@RequestBody @Validated TbRiskHazardsStatisticParamDTO dto) {
        return Result.ok(baseService.gridStatistic(dto));
    }

    @ApiOperation("隐患网格top5")
    @ApiOperationSupport(order = 12)
    @PostMapping("/gridTop5Statistic/{year}")
    public Result<List<RiskHazardsGridTop5StatisticDTO>> gridTop5Statistic(@PathVariable("year") Integer year) {
        return Result.ok(baseService.gridTop5Statistic(year));
    }


    @ApiOperation("列表查询")
    @ApiOperationSupport(order = 13)
    @PostMapping("/getList")
    public Result<List<TbRiskHazardsHandle>> getList() {
        return Result.ok(baseService.list());
    }

}
