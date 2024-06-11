package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
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
import com.geovis.manager.bs.constant.CreditInfoReportConstant;
import com.geovis.manager.bs.dto.TbCreditInfoReportDTO;
import com.geovis.manager.bs.dto.TbCreditInfoReportQueryDTO;
import com.geovis.manager.bs.dto.TbCreditInfoReportReportDTO;
import com.geovis.manager.bs.dto.TbCreditInfoReportSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbCreditInfoReport;
import com.geovis.manager.bs.service.ITbCreditInfoReportService;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.geovis.manager.system.dto.SystemFileDTO;
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

/**
 * <p>
 * 信用信息报送 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/bs/tbCreditInfoReport")
@RequiredArgsConstructor
@Api(value = "信用信息报送接口", tags = "信用信息报送接口")
@Slf4j
@Validated
public class TbCreditInfoReportController extends BaseController<ITbCreditInfoReportService> {

    private final ITbEnterpriseService enterpriseService;

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemFileService systemFileService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbCreditInfoReport>> getPage(@RequestBody @Validated PageParam<TbCreditInfoReportQueryDTO> pageParam) {
        IPage<TbCreditInfoReport> page = pageParam.buildPage();
        TbCreditInfoReportQueryDTO queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbCreditInfoReport> wrapper = Wrappers.lambdaQuery(TbCreditInfoReport.class).orderByDesc(TbCreditInfoReport::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getType()), TbCreditInfoReport::getType, queryDTO.getType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), TbCreditInfoReport::getEnterpriseId, queryDTO.getEnterpriseId());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbCreditInfoReport>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbCreditInfoReport> saveOrUpdate(@RequestBody @Validated TbCreditInfoReportSaveOrUpdateDTO dto) {
        TbCreditInfoReport entity = BeanUtil.copyProperties(dto, TbCreditInfoReport.class);
        if (StrUtil.isEmpty(entity.getReportStatus())) {
            entity.setReportStatus(CommonConstants.NO);
        }
        baseService.saveOrUpdate(entity);
        // 附件处理
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class).eq(SystemFileBusiness::getParam1, entity.getId()));
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(entity.getId())
                        .setParam2(CreditInfoReportConstant.FILE_FLAG_CREDIT_INFO_FILE);
                systemFileBusinessService.save(business);
            });
        }
        return Result.ok(entity);
    }

    @ApiOperation("报送")
    @ApiOperationSupport(order = 2)
    @PostMapping("/report")
    public Result<TbCreditInfoReport> report(@RequestBody @Validated TbCreditInfoReportReportDTO dto) {
        TbCreditInfoReport entity = BeanUtil.copyProperties(dto, TbCreditInfoReport.class);
        entity.setReportStatus(CommonConstants.YES);
        baseService.saveOrUpdate(entity);
        // 附件处理
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class).eq(SystemFileBusiness::getParam1, entity.getId()));
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(entity.getId())
                        .setParam2(CreditInfoReportConstant.FILE_FLAG_CREDIT_INFO_FILE);
                systemFileBusinessService.save(business);
            });
        }
        // 报送附件
        if (CollUtil.isNotEmpty(dto.getReportFileIdList())) {
            dto.getReportFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(entity.getId())
                        .setParam2(CreditInfoReportConstant.FILE_FLAG_CREDIT_INFO_REPORT);
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


    @ApiOperation("根据ID查询企业详情")
    @ApiOperationSupport(order = 4)
    @PostMapping("/getById/{id}")
    public Result<TbCreditInfoReportDTO> getById(@PathVariable("id") String id) {
        TbCreditInfoReport entity = baseService.getById(id);
        TbCreditInfoReportDTO dto = null;
        if (ObjectUtil.isNotEmpty(entity)) {
            dto = BeanUtil.copyProperties(entity, TbCreditInfoReportDTO.class);
            // 查询附件列表
            SystemFileQueryDTO fileQueryDTO = new SystemFileQueryDTO();
            fileQueryDTO.setParam1(entity.getId());
            Map<String, List<SystemFileDTO>> fileListMap = IterUtil.toListMap(systemFileService.getList(fileQueryDTO), SystemFileDTO::getParam2);
            dto.setFileList(fileListMap.get(CreditInfoReportConstant.FILE_FLAG_CREDIT_INFO_FILE))
                    .setReportFileList(fileListMap.get(CreditInfoReportConstant.FILE_FLAG_CREDIT_INFO_REPORT));
        }
        return Result.ok(dto);
    }

}
