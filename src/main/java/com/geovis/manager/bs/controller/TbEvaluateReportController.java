package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbEvaluateReport;
import com.geovis.manager.bs.service.ITbEvaluateReportService;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import com.geovis.manager.system.service.ISystemFileService;
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
 * 评价评估报告表 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/bs/tbEvaluateReport")
@RequiredArgsConstructor
@Api(value = "业务模块-评价评估报告表接口", tags = "业务模块-评价评估报告表接口")
@Slf4j
@Validated
public class TbEvaluateReportController extends BaseController<ITbEvaluateReportService> {

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemFileService systemFileService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbEvaluateReport>> getPage(@RequestBody @Validated PageParam<TbEvaluateReportQueryDTO> pageParam) {
        IPage<TbEvaluateReport> page = pageParam.buildPage();
        TbEvaluateReportQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbEvaluateReport> wrapper = Wrappers.lambdaQuery(TbEvaluateReport.class).orderByDesc(TbEvaluateReport::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbEvaluateReport::getEnterpriseId, queryDTO.getEnterpriseId())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getDangerLevel()), TbEvaluateReport::getDangerLevel, queryDTO.getDangerLevel())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getReportType()), TbEvaluateReport::getReportType, queryDTO.getReportType())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getEvaluateTimeStart()), TbEvaluateReport::getEvaluateTime, queryDTO.getEvaluateTimeStart())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getEvaluateTimeEnd()), TbEvaluateReport::getEvaluateTime, queryDTO.getEvaluateTimeEnd());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbEvaluateReport>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbEvaluateReport> saveOrUpdate(@RequestBody @Validated TbEvaluateReportSaveOrUpdateDTO dto) {
        TbEvaluateReport entity = BeanUtil.copyProperties(dto, TbEvaluateReport.class);
        baseService.saveOrUpdate(entity);
        // 附件处理
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class).eq(SystemFileBusiness::getParam1, entity.getId()));
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(entity.getId());
                systemFileBusinessService.save(business);
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

    @ApiOperation("根据ID查询明细")
    @ApiOperationSupport(order = 4)
    @PostMapping("/getById/{id}")
    public Result<TbEvaluateReportDTO> getById(@PathVariable("id") String id) {
        TbEvaluateReport entity = baseService.getById(id);
        TbEvaluateReportDTO dto = null;
        if (ObjectUtil.isNotEmpty(entity)) {
            dto = BeanUtil.copyProperties(entity, TbEvaluateReportDTO.class);
            // 查询附件列表
            SystemFileQueryDTO fileQueryDTO = new SystemFileQueryDTO();
            fileQueryDTO.setParam1(entity.getId());
            dto.setFileList(systemFileService.getList(fileQueryDTO));
        }
        return Result.ok(dto);
    }

    @ApiOperation("新增或修改评审意见")
    @ApiOperationSupport(order = 5)
    @PostMapping("/reviewComments")
    public Result<?> reviewComments(@Validated @RequestBody TbEvaluateReportReviewCommentsDTO dto) {
        baseService.update(Wrappers.lambdaUpdate(TbEvaluateReport.class)
                .set(TbEvaluateReport::getReviewComments, dto.getReviewComments())
                .eq(TbEvaluateReport::getId, dto.getId()));
        return Result.ok();
    }

    @ApiOperation("根据年度项目、企业统计")
    @ApiOperationSupport(order = 6)
    @GetMapping("/projectStatistic/{year}")
    public Result<ProjectStatisticDTO> projectStatistic(@PathVariable("year") Integer year) {
        List<TbEvaluateReport> list = baseService.list(Wrappers.lambdaQuery(TbEvaluateReport.class)
                .ge(TbEvaluateReport::getEvaluateTime, LocalDateTimeUtil.parse(year + "-01-01 00:00:00", DatePattern.NORM_DATETIME_PATTERN))
                .le(TbEvaluateReport::getEvaluateTime, LocalDateTimeUtil.parse(year + "-12-31 23:59:59", DatePattern.NORM_DATETIME_PATTERN)));
        ProjectStatisticDTO dto = new ProjectStatisticDTO();
        Set<String> enterpriseSet = list.stream().map(TbEvaluateReport::getEnterpriseId).collect(Collectors.toSet());
        dto.setEnterpriseCount(enterpriseSet.size())
                .setProjectCount(list.size());
        return Result.ok();
    }

    @ApiOperation("根据年度、危险等级统计")
    @ApiOperationSupport(order = 7)
    @GetMapping("/dangerStatistic/{year}")
    public Result<List<DangerStatisticDTO>> dangerStatistic(@PathVariable("year") Integer year) {
        List<TbEvaluateReport> list = baseService.list(Wrappers.lambdaQuery(TbEvaluateReport.class)
                .ge(TbEvaluateReport::getEvaluateTime, LocalDateTimeUtil.parse(year + "-01-01 00:00:00", DatePattern.NORM_DATETIME_PATTERN))
                .le(TbEvaluateReport::getEvaluateTime, LocalDateTimeUtil.parse(year + "-12-31 23:59:59", DatePattern.NORM_DATETIME_PATTERN)));
        Map<String, List<TbEvaluateReport>> map = IterUtil.toListMap(list, TbEvaluateReport::getDangerLevel);
        List<DangerStatisticDTO> result = CollUtil.list(false);
        map.forEach((level, item) -> {
            DangerStatisticDTO dto = new DangerStatisticDTO();
            dto.setDangerLevel(level).setCount(item.size());
            result.add(dto);
        });
        return Result.ok(result);
    }

    @ApiOperation("根据年度、报告类型统计")
    @ApiOperationSupport(order = 8)
    @GetMapping("/reportTypeStatistic/{year}")
    public Result<List<ReportTypeStatisticDTO>> reportTypeStatistic(@PathVariable("year") Integer year) {
        List<TbEvaluateReport> list = baseService.list(Wrappers.lambdaQuery(TbEvaluateReport.class)
                .ge(TbEvaluateReport::getEvaluateTime, LocalDateTimeUtil.parse(year + "-01-01 00:00:00", DatePattern.NORM_DATETIME_PATTERN))
                .le(TbEvaluateReport::getEvaluateTime, LocalDateTimeUtil.parse(year + "-12-31 23:59:59", DatePattern.NORM_DATETIME_PATTERN)));
        Map<String, List<TbEvaluateReport>> map = IterUtil.toListMap(list, TbEvaluateReport::getReportType);
        List<ReportTypeStatisticDTO> result = CollUtil.list(false);
        map.forEach((type, item) -> {
            ReportTypeStatisticDTO dto = new ReportTypeStatisticDTO();
            dto.setReportType(type).setCount(item.size());
            result.add(dto);
        });
        return Result.ok(result);
    }

}
