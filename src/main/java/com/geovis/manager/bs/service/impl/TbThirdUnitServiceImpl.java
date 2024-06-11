package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.ThirdUnitConstant;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbThirdUnit;
import com.geovis.manager.bs.entity.TbThirdUnitEnterprise;
import com.geovis.manager.bs.mapper.TbThirdUnitMapper;
import com.geovis.manager.bs.service.ITbThirdUnitEnterpriseService;
import com.geovis.manager.bs.service.ITbThirdUnitService;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import com.geovis.manager.system.service.ISystemFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方单位 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbThirdUnitServiceImpl extends ServiceImpl<TbThirdUnitMapper, TbThirdUnit> implements ITbThirdUnitService {

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ITbThirdUnitEnterpriseService thirdUnitEnterpriseService;

    private final ISystemFileService systemFileService;

    @Override
    public PageResult<TbThirdUnitEnterpriseDTO> getEnterprisePage(PageParam<TbThirdUnitEnterpriseDTO> pageParam) {
        IPage<TbThirdUnitEnterpriseDTO> page = pageParam.buildPage();
        QueryWrapper<Object> wrapper = Wrappers.query();
        TbThirdUnitEnterpriseDTO dto = pageParam.getQuery();
        wrapper.orderByDesc("a.create_time");
        if (ObjectUtil.isNotEmpty(dto)) {
            wrapper.eq(StrUtil.isNotEmpty(dto.getStatus()), "a.status", dto.getStatus())
                    .eq(StrUtil.isNotEmpty(dto.getTrainStatus()), "b.train_status", dto.getTrainStatus())
                    .eq(StrUtil.isNotEmpty(dto.getType()), "a.type", dto.getType());
        }
        baseMapper.getEnterprisePage(page, wrapper);
        return new PageResult<TbThirdUnitEnterpriseDTO>(page);
    }

    @Override
    public PageResult<TbThirdUnitParkDTO> getParkPage(PageParam<TbThirdUnitParkQueryDTO> pageParam) {
        IPage<TbThirdUnitParkDTO> page = pageParam.buildPage();
        QueryWrapper<Object> wrapper = Wrappers.query();
        TbThirdUnitParkQueryDTO dto = pageParam.getQuery();
        wrapper.orderByDesc("a.create_time");
        if (ObjectUtil.isNotEmpty(dto)) {
            wrapper.eq(StrUtil.isNotEmpty(dto.getStatus()), "a.status", dto.getStatus())
                    .eq(StrUtil.isNotEmpty(dto.getQualificationLevel()), "a.qualification_level", dto.getQualificationLevel())
                    .ge(ObjectUtil.isNotEmpty(dto.getExpireDateStart()), "a.expire_date", dto.getExpireDateStart())
                    .le(ObjectUtil.isNotEmpty(dto.getExpireDateEnd()), "a.expire_date", dto.getExpireDateEnd())
                    .exists(StrUtil.isNotEmpty(dto.getEnterpriseId()), "select 1 from tb_third_unit_enterprise b where a.id = b.third_unit_id and enterprise_id = '" + dto.getEnterpriseId() + "'");
        }
        baseMapper.getParkPage(page, wrapper);
        return new PageResult<TbThirdUnitParkDTO>(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadSafeEduRecord(TbThirdUnitUploadSafeEduRecordDTO dto) {
        // 上传数据保存并修改培训状态
        thirdUnitEnterpriseService.update(Wrappers.lambdaUpdate(TbThirdUnitEnterprise.class)
                .set(TbThirdUnitEnterprise::getTrainStatus, CommonConstants.YES)
                .set(TbThirdUnitEnterprise::getTrainContent, dto.getTrainContent())
                .set(TbThirdUnitEnterprise::getTrainTime, dto.getTrainTime())
                .eq(TbThirdUnitEnterprise::getThirdUnitId, dto.getThirdUnitId())
                .eq(TbThirdUnitEnterprise::getEnterpriseId, dto.getEnterpriseId())
        );
        // 附件处理
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class)
                .eq(SystemFileBusiness::getParam2, dto.getEnterpriseId())
                .eq(SystemFileBusiness::getParam3, dto.getThirdUnitId()));
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(ThirdUnitConstant.FILE_FLAG_TRAIN_FILE)
                        .setParam2(dto.getEnterpriseId())
                        .setParam3(dto.getThirdUnitId())
                ;
                systemFileBusinessService.save(business);
            });
        }
        // 文件处理
        if (CollUtil.isNotEmpty(dto.getImageIdList())) {
            dto.getImageIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(ThirdUnitConstant.FILE_FLAG_TRAIN_IMAGE)
                        .setParam2(dto.getEnterpriseId())
                        .setParam3(dto.getThirdUnitId())
                ;
                systemFileBusinessService.save(business);
            });
        }
    }

    @Override
    public TbThirdUnitSafeEduRecordDTO getSafeEduRecord(TbThirdUnitSafeEduRecordQueryDTO dto) {
        TbThirdUnitEnterprise one = thirdUnitEnterpriseService.getOne(Wrappers.lambdaQuery(TbThirdUnitEnterprise.class)
                .eq(TbThirdUnitEnterprise::getEnterpriseId, dto.getEnterpriseId())
                .eq(TbThirdUnitEnterprise::getThirdUnitId, dto.getThirdUnitId()));
        if (ObjectUtil.isNotEmpty(one)) {
            TbThirdUnitSafeEduRecordDTO result = BeanUtil.copyProperties(one, TbThirdUnitSafeEduRecordDTO.class);
            // 查询附件列表(培训附件)
            SystemFileQueryDTO queryDTO = new SystemFileQueryDTO();
            queryDTO.setParam2(dto.getEnterpriseId())
                    .setParam3(dto.getThirdUnitId());
            List<SystemFileDTO> list = systemFileService.getList(queryDTO);
            Map<String, List<SystemFileDTO>> fileListMap = IterUtil.toListMap(list, SystemFileDTO::getParam1);
            result.setFileList(fileListMap.get(ThirdUnitConstant.FILE_FLAG_TRAIN_FILE))
                    .setImageList(fileListMap.get(ThirdUnitConstant.FILE_FLAG_TRAIN_IMAGE));
            return result;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void audit(TbThirdUnitAuditDTO dto) {
        update(Wrappers.lambdaUpdate(TbThirdUnit.class)
                .set(TbThirdUnit::getStatus, dto.getStatus())
                .eq(TbThirdUnit::getId, dto.getThirdUnitId()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(TbThirdUnitRegisterDTO dto) {
        long count = count(Wrappers.lambdaQuery(TbThirdUnit.class).eq(TbThirdUnit::getName, dto.getName()));
        Assert.isFalse(count > 0, "第三方单位信息已存在！");
        TbThirdUnit tbThirdUnit = BeanUtil.copyProperties(dto, TbThirdUnit.class);
        tbThirdUnit.setStatus(ThirdUnitConstant.STATUS_BE_AUDIT);
        save(tbThirdUnit);
        List<TbThirdUnitEnterprise> list = CollUtil.list(false);
        dto.getEnterpriseIdList().forEach(enterpriseId -> {
            TbThirdUnitEnterprise thirdUnitEnterprise = new TbThirdUnitEnterprise();
            thirdUnitEnterprise.setThirdUnitId(tbThirdUnit.getId())
                    .setEnterpriseId(enterpriseId)
                    .setTrainStatus(CommonConstants.NO);
            list.add(thirdUnitEnterprise);
        });
        thirdUnitEnterpriseService.saveBatch(list);
        // 资质材料
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(ThirdUnitConstant.FILE_FLAG_QUALIFICATION)
                        .setParam2(tbThirdUnit.getId())
                ;
                systemFileBusinessService.save(business);
            });
        }
    }
}
