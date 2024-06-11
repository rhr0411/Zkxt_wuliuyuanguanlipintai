package com.geovis.manager.bs.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.bs.constant.MessageTypeConstant;
import com.geovis.manager.bs.entity.TbCommitment;
import com.geovis.manager.bs.entity.TbMessage;
import com.geovis.manager.bs.mapper.TbCommitmentMapper;
import com.geovis.manager.bs.service.ITbCommitmentService;
import com.geovis.manager.bs.service.ITbMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 安全承诺书 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbCommitmentServiceImpl extends ServiceImpl<TbCommitmentMapper, TbCommitment> implements ITbCommitmentService {

    private final ITbMessageService tbMessageService;

    @Override
    public void checkYearPro() {
        DateTime dateTime = DateUtil.offsetDay(new Date(), 7);
        List<TbCommitment> list = list(Wrappers.lambdaQuery(TbCommitment.class)
                .eq(TbCommitment::getCommitEndDate, LocalDate.of(dateTime.year(), dateTime.month(), dateTime.dayOfMonth())));
        if (CollUtil.isNotEmpty(list)) {
            for (TbCommitment tbCommitment : list) {
                // TODO
                TbMessage message = new TbMessage();
                message.setContent(MessageTypeConstant.COMMITMENT.getMsgTemplate())
                        .setType(MessageTypeConstant.COMMITMENT.getCode())
                        .setEnterpriseId(tbCommitment.getEnterpriseId())
                        .setDataTime(LocalDateTime.now());
                tbMessageService.save(message);
            }
        }
    }
}
