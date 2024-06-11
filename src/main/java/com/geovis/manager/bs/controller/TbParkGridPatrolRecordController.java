package com.geovis.manager.bs.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbParkGridPatrolRecord;
import com.geovis.manager.bs.service.ITbParkGridPatrolRecordService;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 园区网格_巡检记录 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/bs/tbParkGridPatrolRecord")
@RequiredArgsConstructor
@Api(value = "园区网格_巡检记录接口", tags = "园区网格_巡检记录接口")
@Slf4j
@Validated
public class TbParkGridPatrolRecordController extends BaseController<ITbParkGridPatrolRecordService> {

    private final ITbParkGridPatrolTmpService parkGridPatrolTmpService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbParkGridPatrolRecord>> getPage(@RequestBody @Validated PageParam<TbParkGridPatrolRecordQueryDTO> pageParam) {
        IPage<TbParkGridPatrolRecord> page = pageParam.buildPage();
        TbParkGridPatrolRecordQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbParkGridPatrolRecord> wrapper = Wrappers.lambdaQuery(TbParkGridPatrolRecord.class).orderByDesc(TbParkGridPatrolRecord::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getGridId()), TbParkGridPatrolRecord::getGridId, queryDTO.getGridId())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getPatrolStatus()), TbParkGridPatrolRecord::getPatrolStatus, queryDTO.getPatrolStatus());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbParkGridPatrolRecord>(page));
    }

    @ApiOperation("详情查询")
    @ApiOperationSupport(order = 2)
    @GetMapping("/getDetailById/{id}")
    public Result<TbParkGridPatrolRecordDetailDTO> getDetailById(@PathVariable("id") String id) {
        TbParkGridPatrolRecordDetailDTO dto = baseService.getDetailById(id);
        dto.setTmpDTO(parkGridPatrolTmpService.getEntityById(dto.getPatrolTmpId()));
        return Result.ok(dto);
    }

    @ApiOperation("开始巡查")
    @ApiOperationSupport(order = 3)
    @PostMapping("/beginPatrol/{patrolId}")
    public Result<TbParkGridPatrolRecord> beginPatrol(@PathVariable("patrolId") String patrolId) {
        TbParkGridPatrolRecord entity = baseService.getById(patrolId);
        entity.setPatrolActualStartTime(LocalDateTime.now());
        baseService.updateById(entity);
        return Result.ok(entity);
    }

    @ApiOperation("完成巡查")
    @ApiOperationSupport(order = 4)
    @PostMapping("/completePatrol")
    public Result<?> completePatrol(@RequestBody @Validated TbParkGridPatrolCompleteDTO dto) {
        baseService.completePatrol(dto);
        return Result.ok();
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 5)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("网格已完成数量统计")
    @ApiOperationSupport(order = 6)
    @GetMapping("/gridStatistic/{year}")
    public Result<List<StatisticDTO>> gridStatistic(@PathVariable("year") Integer year) {
        List<StatisticDTO> list = baseService.gridStatistic(year);
        return Result.ok(list);
    }

    @ApiOperation("记录数量统计")
    @ApiOperationSupport(order = 7)
    @GetMapping("/numStatistic/{year}")
    public Result<TbParkGridPatrolRecordStsDTO> numStatistic(@PathVariable("year") Integer year) {
        return Result.ok(baseService.numStatistic(year));
    }

    @ApiOperation("附件数量统计")
    @ApiOperationSupport(order = 8)
    @GetMapping("/fileStatistic/{year}")
    public Result<TbParkGridPatrolRecordFileStsDTO> fileStatistic(@PathVariable("year") Integer year) {
        return Result.ok(baseService.fileStatistic(year));
    }

}
