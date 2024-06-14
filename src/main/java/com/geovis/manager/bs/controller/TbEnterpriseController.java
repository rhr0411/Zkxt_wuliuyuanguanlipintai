package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
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
import com.geovis.manager.bs.constant.MessageTypeConstant;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.bs.entity.TbMessage;
import com.geovis.manager.bs.entity.TbRiskHazardsHandle;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.geovis.manager.bs.service.ITbMessageService;
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

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 企业信息 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/bs/tbEnterprise")
@RequiredArgsConstructor
@Api(value = "业务模块-企业信息接口", tags = "业务模块-企业信息接口接口")
@Slf4j
@Validated
public class TbEnterpriseController extends BaseController<ITbEnterpriseService> {

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemFileService systemFileService;

    @Resource
    private ITbMessageService tbMessageService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbEnterprise>> getPage(@RequestBody @Validated PageParam<TbEnterpriseQueryDTO> pageParam) {
        IPage<TbEnterprise> page = pageParam.buildPage();
        TbEnterpriseQueryDTO queryDTO = pageParam.getQuery();
        baseService.page(page, getWrapperQuery(queryDTO));
        return Result.ok(new PageResult<TbEnterprise>(page));
    }

    @ApiOperation("列表查询")
    @ApiOperationSupport(order = 2)
    @PostMapping("/getList")
    public Result<List<TbEnterprise>> getList(@RequestBody @Validated TbEnterpriseQueryDTO queryDTO) {
        return Result.ok(baseService.list(getWrapperQuery(queryDTO)));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 3)
    @PostMapping("/saveOrUpdate")
    public Result<TbEnterprise> saveOrUpdate(@RequestBody @Validated TbEnterpriseSaveOrUpdateDTO dto) {
        TbEnterprise entity = BeanUtil.copyProperties(dto, TbEnterprise.class);
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

    @ApiOperation("黑名单保存或更新")
    @ApiOperationSupport(order = 4)
    @PostMapping("/blacklistSaveOrUpdate")
    public Result<?> blacklistSaveOrUpdate(@RequestBody @Validated TbEnterpriseBlacklistSaveOrUpdateDTO dto) {
        baseService.update(Wrappers.lambdaUpdate(TbEnterprise.class)
                .set(TbEnterprise::getBlacklistRemark, dto.getBlacklistRemark())
                .set(TbEnterprise::getInBlacklist, CommonConstants.YES)
                .eq(TbEnterprise::getId, dto.getId())
        );
        return Result.ok();
    }

    @ApiOperation("移除黑名单")
    @ApiOperationSupport(order = 5)
    @PostMapping("/removeBlacklist/{id}")
    public Result<?> removeBlacklist(@PathVariable("id") String id) {
        baseService.update(Wrappers.lambdaUpdate(TbEnterprise.class)
                .set(TbEnterprise::getBlacklistRemark, null)
                .set(TbEnterprise::getInBlacklist, CommonConstants.NO)
                .eq(TbEnterprise::getId, id)
        );
        return Result.ok();
    }

    @ApiOperation("编辑诚信等级")
    @ApiOperationSupport(order = 6)
    @PostMapping("/editIntegrityLevel")
    public Result<?> editIntegrityLevel(@RequestBody @Validated TbEnterpriseIntegrityLevelEditDTO dto) {
        TbEnterprise enterprise = baseService.getById(dto.getId());
        if (!StrUtil.equals(enterprise.getIntegrityLevel(), dto.getIntegrityLevel())) {
            // TODO 发送短信通知
                TbMessage message = new TbMessage();
                message.setContent(MessageTypeConstant.RISK_HAZARDS.getMsgTemplate())
                        .setType(MessageTypeConstant.RISK_HAZARDS.getCode())
                        .setEnterpriseId(enterprise.getId())
                        .setDataTime(LocalDateTime.now());
                tbMessageService.save(message);
        }
        enterprise.setIntegrityLevel(dto.getIntegrityLevel())
                .setIntegrityLevelRemark(dto.getIntegrityLevelRemark());
        baseService.updateById(enterprise);
        return Result.ok();
    }

    @ApiOperation("根据ID删除企业")
    @ApiOperationSupport(order = 7)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        // todo 后边需要关联删除相关的表
        return Result.ok();
    }

    @ApiOperation("根据ID查询企业详情")
    @ApiOperationSupport(order = 8)
    @PostMapping("/getById/{id}")
    public Result<TbEnterpriseDTO> getById(@PathVariable("id") String id) {
        TbEnterprise entity = baseService.getById(id);
        TbEnterpriseDTO tbEnterpriseDTO = null;
        if (ObjectUtil.isNotEmpty(entity)) {
            tbEnterpriseDTO = BeanUtil.copyProperties(entity, TbEnterpriseDTO.class);
            // 查询附件列表
            SystemFileQueryDTO fileQueryDTO = new SystemFileQueryDTO();
            fileQueryDTO.setParam1(entity.getId());
            tbEnterpriseDTO.setFileList(systemFileService.getList(fileQueryDTO));
        }
        return Result.ok(tbEnterpriseDTO);
    }

    @ApiOperation("可视化端-企业数量统计")
    @ApiOperationSupport(order = 9)
    @PostMapping("/statisticCount")
    public Result<Long> statisticCount() {
        long count = baseService.count();
        return Result.ok(count);
    }

    /**
     * 获取查询条件
     *
     * @param queryDTO
     * @return
     */
    private LambdaQueryWrapper<TbEnterprise> getWrapperQuery(TbEnterpriseQueryDTO queryDTO) {
        LambdaQueryWrapper<TbEnterprise> wrapper = Wrappers.lambdaQuery(TbEnterprise.class).orderByDesc(TbEnterprise::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getIndustry()), TbEnterprise::getIndustry, queryDTO.getIndustry())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getEconomicType()), TbEnterprise::getEconomicType, queryDTO.getEconomicType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getIntegrityLevel()), TbEnterprise::getIntegrityLevel, queryDTO.getIntegrityLevel())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getName()), TbEnterprise::getName, queryDTO.getName())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getCreateTimeStart()), TbEnterprise::getCreateTime, queryDTO.getCreateTimeStart())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getCreateTimeEnd()), TbEnterprise::getCreateTime, queryDTO.getCreateTimeEnd())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getSuperviseType()), TbEnterprise::getSuperviseType, queryDTO.getSuperviseType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getInBlacklist()), TbEnterprise::getInBlacklist, queryDTO.getInBlacklist());
        }
        return wrapper;
    }


    @ApiOperation("可视化端-企业类别数量统计")
    @ApiOperationSupport(order = 10)
    @PostMapping("/statisticCountByType")
    public Result<List<TbEnterpriseQueryTypeDTO>> statisticCountByType() {
        List<TbEnterpriseQueryTypeDTO> list = new ArrayList<>();
        long count = baseService.count();
        for(int i=0;i<3;i++){
            TbEnterpriseQueryTypeDTO tbEnterpriseQueryTypeDTO = new TbEnterpriseQueryTypeDTO();
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<TbEnterprise>().eq(TbEnterprise::getSuperviseType,String.valueOf(i+1));
            long counti = baseService.count(lambdaQueryWrapper);
            tbEnterpriseQueryTypeDTO.setSuperviseType(String.valueOf(i+1));
            tbEnterpriseQueryTypeDTO.setNum(String.valueOf(counti));
            if(count!=0) {
                tbEnterpriseQueryTypeDTO.setRate(BigDecimal.valueOf(NumberUtil.div(counti, count, 2)));
            }else {
                tbEnterpriseQueryTypeDTO.setRate(new BigDecimal(0));
            }
            list.add(tbEnterpriseQueryTypeDTO);
        }


        return Result.ok(list);
    }

}
