package com.geovis.manager.bs.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.ThirdUnitConstant;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbThirdUnit;
import com.geovis.manager.bs.service.ITbThirdUnitService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方单位 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/bs/tbThirdUnit")
@RequiredArgsConstructor
@Api(value = "业务模块-第三方单位接口", tags = "业务模块-第三方单位接口")
@Slf4j
@Validated
public class TbThirdUnitController extends BaseController<ITbThirdUnitService> {

    @ApiOperation("企业端-分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getEnterprisePage")
    public Result<PageResult<TbThirdUnitEnterpriseDTO>> getEnterprisePage(@RequestBody @Validated PageParam<TbThirdUnitEnterpriseDTO> pageParam) {
        return Result.ok(baseService.getEnterprisePage(pageParam));
    }

    @ApiOperation("企业端-上传安全教育记录")
    @ApiOperationSupport(order = 2)
    @PostMapping("/uploadSafeEduRecord")
    public Result<?> uploadSafeEduRecord(@RequestBody @Validated TbThirdUnitUploadSafeEduRecordDTO dto) {
        baseService.uploadSafeEduRecord(dto);
        return Result.ok();
    }

    @ApiOperation("企业端-安全教育记录查询")
    @ApiOperationSupport(order = 3)
    @PostMapping("/getSafeEduRecord")
    public Result<TbThirdUnitSafeEduRecordDTO> getSafeEduRecord(@RequestBody @Validated TbThirdUnitSafeEduRecordQueryDTO dto) {
        return Result.ok(baseService.getSafeEduRecord(dto));
    }

    @ApiOperation("园区端-分页查询")
    @ApiOperationSupport(order = 4)
    @PostMapping("/getParkPage")
    public Result<PageResult<TbThirdUnitParkDTO>> getParkPage(@RequestBody @Validated PageParam<TbThirdUnitParkQueryDTO> pageParam) {
        return Result.ok(baseService.getParkPage(pageParam));
    }

    @ApiOperation("园区端-审核")
    @ApiOperationSupport(order = 5)
    @PostMapping("/audit")
    public Result<?> audit(@RequestBody @Validated TbThirdUnitAuditDTO dto) {
        baseService.audit(dto);
        return Result.ok();
    }

    @ApiOperation("园区端-档案库数量")
    @ApiOperationSupport(order = 6)
    @GetMapping("/getArchiveCount")
    public Result<?> getArchiveCount() {
        long count = baseService.count(Wrappers.lambdaQuery(TbThirdUnit.class).eq(TbThirdUnit::getStatus, ThirdUnitConstant.STATUS_PASS));
        return Result.ok(count);
    }

    @ApiOperation("园区端-单位类别统计")
    @ApiOperationSupport(order = 7)
    @GetMapping("/typeStatistic")
    public Result<List<TbThirdUnitTypeStatisticDTO>> typeStatistic() {
        List<TbThirdUnit> list = baseService.list(Wrappers.lambdaQuery(TbThirdUnit.class).select(TbThirdUnit::getType, TbThirdUnit::getId));
        List<TbThirdUnitTypeStatisticDTO> result = CollUtil.list(false);
        Map<String, List<TbThirdUnit>> map = IterUtil.toListMap(list, TbThirdUnit::getType);
        map.forEach((key, value) -> {
            TbThirdUnitTypeStatisticDTO dto = new TbThirdUnitTypeStatisticDTO();
            dto.setType(key)
                    .setCount(value.size());
            result.add(dto);
        });
        return Result.ok(result);
    }

    @ApiOperation("园区端-单位等级统计")
    @ApiOperationSupport(order = 8)
    @GetMapping("/levelStatistic")
    public Result<List<TbThirdUnitLevelStatisticDTO>> levelStatistic() {
        List<TbThirdUnit> list = baseService.list(Wrappers.lambdaQuery(TbThirdUnit.class).select(TbThirdUnit::getQualificationLevel, TbThirdUnit::getId));
        List<TbThirdUnitLevelStatisticDTO> result = CollUtil.list(false);
        Map<String, List<TbThirdUnit>> map = IterUtil.toListMap(list, TbThirdUnit::getQualificationLevel);
        map.forEach((key, value) -> {
            TbThirdUnitLevelStatisticDTO dto = new TbThirdUnitLevelStatisticDTO();
            dto.setLevel(key)
                    .setCount(value.size());
            result.add(dto);
        });
        return Result.ok(result);
    }

    @ApiOperation("园区端-邮件提醒")
    @ApiOperationSupport(order = 9)
    @PostMapping("/emailRemind")
    public Result<?> emailRemind(@Validated @RequestBody TbThirdUnitEmailRemindDTO dto) {
        // TODO
        return Result.ok();
    }

    @ApiOperation("移动端-单位注册")
    @ApiOperationSupport(order = 10)
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody TbThirdUnitRegisterDTO dto) {
        baseService.register(dto);
        return Result.ok();
    }

    @ApiOperation("移动端-分页查询(档案库)")
    @ApiOperationSupport(order = 11)
    @PostMapping("/getAppPage")
    public Result<PageResult<TbThirdUnit>> getAppPage(@RequestBody @Validated PageParam<TbThirdUnit> pageParam) {
        IPage<TbThirdUnit> page = pageParam.buildPage();
        LambdaQueryWrapper<TbThirdUnit> wrapper = Wrappers.lambdaQuery(TbThirdUnit.class);
        TbThirdUnit dto = pageParam.getQuery();
        wrapper.eq(TbThirdUnit::getStatus, ThirdUnitConstant.STATUS_PASS).orderByDesc(TbThirdUnit::getCreateTime);
        if (ObjectUtil.isNotEmpty(dto)) {
            wrapper.eq(StrUtil.isNotEmpty(dto.getName()), TbThirdUnit::getName, dto.getName())
                    .eq(StrUtil.isNotEmpty(dto.getMasterName()), TbThirdUnit::getMasterName, dto.getMasterName())
                    .eq(StrUtil.isNotEmpty(dto.getMasterPhone()), TbThirdUnit::getMasterPhone, dto.getMasterPhone());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbThirdUnit>(page));
    }

}
