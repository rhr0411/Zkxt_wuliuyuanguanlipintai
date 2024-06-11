package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.MessageTypeConstant;
import com.geovis.manager.bs.constant.RiskHazardsConstant;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbMessage;
import com.geovis.manager.bs.entity.TbRiskHazardsHandle;
import com.geovis.manager.bs.mapper.TbRiskHazardsHandleMapper;
import com.geovis.manager.bs.service.ITbMessageService;
import com.geovis.manager.bs.service.ITbRiskHazardsHandleService;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import com.geovis.manager.system.service.ISystemFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 风险隐患管理_隐患整改 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbRiskHazardsHandleServiceImpl extends ServiceImpl<TbRiskHazardsHandleMapper, TbRiskHazardsHandle> implements ITbRiskHazardsHandleService {

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemFileService systemFileService;

    private final ITbMessageService tbMessageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbRiskHazardsHandle appSave(TbRiskHazardsAppSaveDTO dto) {
        TbRiskHazardsHandle entity = BeanUtil.copyProperties(dto, TbRiskHazardsHandle.class);
        entity.setReportTime(LocalDateTime.now())
                .setDataSource(RiskHazardsConstant.DATA_SOURCE_RANDOM_CLAPPING)
                .setHandleStatus(CommonConstants.NO);
        save(entity);
        // 附件处理
        if (CollUtil.isNotEmpty(dto.getRiskHazardsIdList())) {
            dto.getRiskHazardsIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_REPORTER)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        addMessage(entity);
        return entity;
    }

    /**
     * 新增消息提醒
     *
     * @param entity
     */
    private void addMessage(TbRiskHazardsHandle entity) {
        if (ObjectUtil.isEmpty(entity.getEnterpriseId())) {
            return;
        }
        TbMessage message = new TbMessage();
        message.setContent(MessageTypeConstant.RISK_HAZARDS.getMsgTemplate())
                .setType(MessageTypeConstant.RISK_HAZARDS.getCode())
                .setEnterpriseId(entity.getEnterpriseId())
                .setDataTime(LocalDateTime.now());
        tbMessageService.save(message);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbRiskHazardsHandle supervision(TbRiskHazardsSupervisionDTO dto) {
        TbRiskHazardsHandle entity = getById(dto.getId());
        BeanUtil.copyProperties(dto, entity);
        entity.setHandleStatus(CommonConstants.YES)
                .setSupervisionStatus(RiskHazardsConstant.SUPERVISION_STATUS_BE_DOING);
        updateById(entity);
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class)
                .eq(SystemFileBusiness::getParam1, RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_REPORTER)
                .eq(SystemFileBusiness::getParam2, entity.getId()));
        // 附件处理
        if (CollUtil.isNotEmpty(dto.getRiskHazardsIdList())) {
            dto.getRiskHazardsIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_REPORTER)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        addMessage(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbRiskHazardsHandle parkSaveAndSupervision(TbRiskHazardsAddSupervisionDTO dto) {
        TbRiskHazardsHandle entity = BeanUtil.copyProperties(dto, TbRiskHazardsHandle.class);
        entity.setReportTime(LocalDateTime.now())
                .setHandleStatus(CommonConstants.YES)
                .setSupervisionStatus(RiskHazardsConstant.SUPERVISION_STATUS_BE_DOING);
        save(entity);
        // 附件处理
        if (CollUtil.isNotEmpty(dto.getRiskHazardsIdList())) {
            dto.getRiskHazardsIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_REPORTER)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        addMessage(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbRiskHazardsHandle check(TbRiskHazardsCheckDTO dto) {
        TbRiskHazardsHandle entity = getById(dto.getId());
        BeanUtil.copyProperties(dto, entity);
        // 默认整改完成
        String supervisionStatus = RiskHazardsConstant.SUPERVISION_STATUS_COMPLETE;
        // 复查驳回
        if (StrUtil.equals(dto.getCheckStatus(), CommonConstants.NO)) {
            // 变化成待整改
            supervisionStatus = RiskHazardsConstant.SUPERVISION_STATUS_BE_DOING;
            // 判断是否延期
            if (LocalDate.now().compareTo(entity.getHandlePlanEndDate()) > 0) {
                // 变化成延期未整改
                supervisionStatus = RiskHazardsConstant.SUPERVISION_STATUS_OVER_NOT_HANDLE;
            }
        }
        entity.setSupervisionStatus(supervisionStatus);
        updateById(entity);
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class)
                .in(SystemFileBusiness::getParam1, RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_CHECK_FILE, RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_CHECK_IMAGE)
                .eq(SystemFileBusiness::getParam2, entity.getId()));
        // 附件处理
        if (CollUtil.isNotEmpty(dto.getCheckFileIdList())) {
            dto.getCheckFileIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_CHECK_FILE)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        if (CollUtil.isNotEmpty(dto.getCheckImageIdList())) {
            dto.getCheckImageIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_CHECK_IMAGE)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbRiskHazardsHandle handle(TbRiskHazardsHandleDTO dto) {
        TbRiskHazardsHandle entity = getById(dto.getId());
        BeanUtil.copyProperties(dto, entity);
        entity.setHandleStatus(RiskHazardsConstant.SUPERVISION_STATUS_BE_CHECK);
        updateById(entity);
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class)
                .in(SystemFileBusiness::getParam1, RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_HANDLE_FILE, RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_HANDLE_IMAGE)
                .eq(SystemFileBusiness::getParam2, entity.getId()));
        // 附件处理
        if (CollUtil.isNotEmpty(dto.getHandleFileIdList())) {
            dto.getHandleFileIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_HANDLE_FILE)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        if (CollUtil.isNotEmpty(dto.getHandleImageIdList())) {
            dto.getHandleImageIdList().forEach(fileId -> {
                SystemFileBusiness systemFileBusiness = new SystemFileBusiness();
                systemFileBusiness.setFileId(fileId)
                        .setParam1(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_HANDLE_IMAGE)
                        .setParam2(entity.getId());
                systemFileBusinessService.save(systemFileBusiness);
            });
        }
        return entity;
    }

    @Override
    public TbRiskHazardsDTO getRiskHazardsById(String id) {
        TbRiskHazardsHandle entity = getById(id);
        if (ObjectUtil.isNotEmpty(entity)) {
            TbRiskHazardsDTO dto = BeanUtil.copyProperties(entity, TbRiskHazardsDTO.class);
            // 查询附件
            SystemFileQueryDTO queryDTO = new SystemFileQueryDTO();
            queryDTO.setParam2(id);
            Map<String, List<SystemFileDTO>> map = IterUtil.toListMap(systemFileService.getList(queryDTO), SystemFileDTO::getParam1);
            dto.setReportImageList(map.get(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_REPORTER))
                    .setHandleFileList(map.get(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_HANDLE_FILE))
                    .setHandleImageList(map.get(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_HANDLE_IMAGE))
                    .setCheckFileList(map.get(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_CHECK_FILE))
                    .setCheckImageList(map.get(RiskHazardsConstant.FILE_FLAG_RISK_HAZARDS_CHECK_IMAGE))
            ;
        }
        return null;
    }

    @Override
    public PageResult<TbRiskHazardsDTO> getRiskHazardsPage(PageParam<TbRiskHazardsQueryDTO> pageParam) {
        IPage<TbRiskHazardsDTO> page = pageParam.buildPage();
        TbRiskHazardsQueryDTO queryDTO = pageParam.getQuery();
        QueryWrapper<Object> wrapper = Wrappers.query();
        wrapper.eq("1", 1).orderByDesc("a.update_time").orderByDesc("a.create_time");
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), "a.enterprise_id", queryDTO.getEnterpriseId())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getHandleStatus()), "a.handle_status", queryDTO.getHandleStatus())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getLevel()), "a.level", queryDTO.getLevel())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getSupervisionStatus()), "a.supervision_status", queryDTO.getSupervisionStatus())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getIndustry()), "b.industry", queryDTO.getIndustry())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getReportTimeStart()), "a.report_time", queryDTO.getReportTimeStart())
                    .le(ObjectUtil.isNotEmpty(queryDTO.getReportTimeEnd()), "a.report_time", queryDTO.getReportTimeEnd())
                    .apply(StrUtil.isNotEmpty(queryDTO.getReportTimeTypeKey()) && StrUtil.isNotEmpty(queryDTO.getReportTimeTypeValue()), StrUtil.format("and date_part('{}',a.report_time) = {}", queryDTO.getReportTimeTypeKey(), queryDTO.getReportTimeTypeValue()));
        }
        baseMapper.selectRiskHazardsPage(page, wrapper);
        return new PageResult<>(page);
    }

    @Override
    public List<StatisticDTO> yearMonthStatistic(TbRiskHazardsStatisticParamDTO dto) {
        return baseMapper.selectYearMonthStatistic(dto);
    }

    @Override
    public List<StatisticDTO> industryStatistic(TbRiskHazardsStatisticParamDTO dto) {
        return baseMapper.selectIndustryStatistic(dto);
    }

    @Override
    public List<StatisticDTO> levelStatistic(TbRiskHazardsStatisticParamDTO dto) {
        return baseMapper.selectLevelStatistic(dto);
    }

    @Override
    public List<StatisticDTO> statusStatistic(TbRiskHazardsStatisticParamDTO dto) {
        // 查询隐患
        List<TbRiskHazardsHandle> list = list(Wrappers.lambdaQuery(TbRiskHazardsHandle.class)
                .select(TbRiskHazardsHandle::getId,TbRiskHazardsHandle::getSupervisionStatus)
                .apply(StrUtil.format("date_part('year',report_time) = '{}'", dto.getYear()))
                .eq(StrUtil.isNotEmpty(dto.getEnterpriseId()), TbRiskHazardsHandle::getEnterpriseId, dto.getEnterpriseId()));
        int hasCheck = 0;
        for (TbRiskHazardsHandle tbRiskHazardsHandle : list) {
            if (StrUtil.equals(tbRiskHazardsHandle.getSupervisionStatus(), RiskHazardsConstant.SUPERVISION_STATUS_BE_CHECK) ||
                    StrUtil.equals(tbRiskHazardsHandle.getSupervisionStatus(), RiskHazardsConstant.SUPERVISION_STATUS_COMPLETE)) {
                hasCheck++;
            }
        }
        StatisticDTO hasCheckDto = new StatisticDTO();
        hasCheckDto.setKey("已整改").setCount(hasCheck);
        StatisticDTO notCheckDto = new StatisticDTO();
        notCheckDto.setKey("未整改").setCount(list.size() - hasCheck);
        return CollUtil.newArrayList(hasCheckDto, notCheckDto);
    }

    @Override
    public List<StatisticDTO> gridStatistic(TbRiskHazardsStatisticParamDTO dto) {
        return baseMapper.selectGridStatistic(dto);
    }

    @Override
    public List<RiskHazardsGridTop5StatisticDTO> gridTop5Statistic(Integer year) {
        return baseMapper.selectGridTop5Statistic(year);
    }


}
