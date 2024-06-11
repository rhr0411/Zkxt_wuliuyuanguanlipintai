package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.manager.bs.constant.MessageTypeConstant;
import com.geovis.manager.bs.dto.TbEqpCheckDTO;
import com.geovis.manager.bs.dto.TbEqpCheckSaveOrUpdateDTO;
import com.geovis.manager.bs.dto.TbEqpCheckStatisticDTO;
import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.bs.entity.TbEqpCheck;
import com.geovis.manager.bs.entity.TbMessage;
import com.geovis.manager.bs.mapper.TbEqpCheckMapper;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.geovis.manager.bs.service.ITbEqpCheckService;
import com.geovis.manager.bs.service.ITbMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 装置开停车和大检修管理 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbEqpCheckServiceImpl extends ServiceImpl<TbEqpCheckMapper, TbEqpCheck> implements ITbEqpCheckService {

    private final ITbEnterpriseService enterpriseService;

    private final ITbMessageService tbMessageService;

    @Override
    public TbEqpCheck saveOrUpdateEntity(TbEqpCheckSaveOrUpdateDTO dto) {
        TbEqpCheck entity = BeanUtil.copyProperties(dto, TbEqpCheck.class);
        TbEnterprise enterprise = enterpriseService.getById(dto.getEnterpriseId());
        if (ObjectUtil.isNotEmpty(enterprise)) {
            entity.setEnterpriseName(enterprise.getName());
        }
        entity.setStatus(CommonConstants.YES);
        List<TbEqpCheck> list = getWarnList(entity);
        Set<String> waringEnterpriseSet = CollUtil.newHashSet();
        if (CollUtil.isNotEmpty(list)) {
            // 设置自己的开停车为预警状态
            entity.setStatus(CommonConstants.NO);
            waringEnterpriseSet.add(entity.getEnterpriseId());
            list.forEach(item -> {
                // 设置其他的开停车为预警状态
                item.setStatus(CommonConstants.NO);
                waringEnterpriseSet.add(item.getEnterpriseId());
                updateById(item);
            });
        }
        saveOrUpdate(entity);
        // 新增消息提醒
        if (CollUtil.isNotEmpty(waringEnterpriseSet)) {
            for (String enterpriseId : waringEnterpriseSet) {
                addMessage(enterpriseId);
            }
        }
        return entity;
    }

    /**
     * 新增消息提醒
     *
     * @param enterpriseId
     */
    private void addMessage(String enterpriseId) {
        if (ObjectUtil.isEmpty(enterpriseId)) {
            return;
        }
        TbMessage message = new TbMessage();
        message.setContent(MessageTypeConstant.EQP.getMsgTemplate())
                .setType(MessageTypeConstant.EQP.getCode())
                .setEnterpriseId(enterpriseId)
                .setDataTime(LocalDateTime.now());
        tbMessageService.save(message);
    }

    @Override
    public TbEqpCheckDTO getEntityById(String id) {
        TbEqpCheck entity = getById(id);
        if (ObjectUtil.isNotEmpty(entity)) {
            TbEqpCheckDTO dto = BeanUtil.copyProperties(entity, TbEqpCheckDTO.class);
            dto.setWarnList(getWarnList(entity));
            return dto;
        }
        return null;
    }

    @Override
    public TbEqpCheckStatisticDTO statistic() {
        TbEqpCheckStatisticDTO dto = new TbEqpCheckStatisticDTO();
        dto.setTotalNum((int) count())
                .setCompleteNum((int) count(Wrappers.lambdaQuery(TbEqpCheck.class).gt(TbEqpCheck::getCheckTimeEnd, LocalDateTime.now())))
                .setDoingNum((int) count(Wrappers.lambdaQuery(TbEqpCheck.class).ge(TbEqpCheck::getCheckTimeStart, LocalDateTime.now()).le(TbEqpCheck::getCheckTimeEnd, LocalDateTime.now())))
                .setWaringNum((int) count(Wrappers.lambdaQuery(TbEqpCheck.class).eq(TbEqpCheck::getStatus, CommonConstants.NO)));
        return dto;
    }

    /**
     * 获取告警列表
     *
     * @param entity
     * @return
     */
    private List<TbEqpCheck> getWarnList(TbEqpCheck entity) {
        return list(Wrappers.lambdaQuery(TbEqpCheck.class)
                .eq(TbEqpCheck::getCheckAddress, entity.getCheckAddress())
                .and(wp -> wp.ge(TbEqpCheck::getCheckTimeStart, entity.getCheckTimeStart()).le(TbEqpCheck::getCheckTimeEnd, entity.getCheckTimeStart())
                        .or()
                        .ge(TbEqpCheck::getCheckTimeStart, entity.getCheckTimeEnd()).le(TbEqpCheck::getCheckTimeEnd, entity.getCheckTimeEnd()))
                .ne(StrUtil.isNotEmpty(entity.getId()), TbEqpCheck::getId, entity.getId())
        );
    }
}
