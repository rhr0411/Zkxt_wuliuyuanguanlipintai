package com.geovis.manager.bs.service.impl;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.manager.bs.dto.TbExamEndAnswerDTO;
import com.geovis.manager.bs.dto.TbExamEndResultDTO;
import com.geovis.manager.bs.dto.TbExamUserAnswerDTO;
import com.geovis.manager.bs.entity.TbExamQuestionRecord;
import com.geovis.manager.bs.entity.TbExamRecord;
import com.geovis.manager.bs.entity.TbExamResult;
import com.geovis.manager.bs.mapper.TbExamRecordMapper;
import com.geovis.manager.bs.props.ExamProperties;
import com.geovis.manager.bs.service.ITbExamQuestionRecordService;
import com.geovis.manager.bs.service.ITbExamRecordService;
import com.geovis.manager.bs.service.ITbExamResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbExamRecordServiceImpl extends ServiceImpl<TbExamRecordMapper, TbExamRecord> implements ITbExamRecordService {

    private final ITbExamQuestionRecordService examQuestionRecordService;

    private final ExamProperties examProperties;

    private final ITbExamResultService examResultService;

    @Override
    public List<TbExamQuestionRecord> beginExam(String currentUserId) {
        // 删除上次未完成的考试试题
        TbExamRecord record = getOne(Wrappers.lambdaQuery(TbExamRecord.class).eq(TbExamRecord::getUserId, currentUserId).eq(TbExamRecord::getStatus, CommonConstants.NO));
        if (ObjectUtil.isNotEmpty(record)) {
            removeById(record.getId());
            examQuestionRecordService.remove(Wrappers.lambdaQuery(TbExamQuestionRecord.class).eq(TbExamQuestionRecord::getExamRecordId, record.getId()));
        }
        // 生成试卷
        record = new TbExamRecord();
        record.setExamDate(LocalDate.now())
                .setExamScore(0)
                .setResult(CommonConstants.NO)
                .setStatus(CommonConstants.NO)
                .setUserId(currentUserId);
        save(record);
        // 生成试卷试题
        List<TbExamQuestionRecord> list = examQuestionRecordService.genQuestionList(record.getId(), currentUserId);
        // 把答案去掉
        list.forEach(item -> item.setExamAnswer(null));
        return list;
    }

    @Override
    public TbExamEndResultDTO endExam(TbExamEndAnswerDTO dto) {
        TbExamRecord record = getById(dto.getExamRecordId());
        Assert.isFalse(ObjectUtil.isEmpty(record), "您已重新考试！");
        // 计算得分
        List<TbExamQuestionRecord> questionRecordList = examQuestionRecordService.list(Wrappers.lambdaQuery(TbExamQuestionRecord.class).eq(TbExamQuestionRecord::getExamRecordId, dto.getExamRecordId()));
        Map<String, TbExamUserAnswerDTO> userAnswerDTOMap = IterUtil.toMap(dto.getUserAnswerList(), TbExamUserAnswerDTO::getExamQuestionId);
        Integer score = 0;
        for (TbExamQuestionRecord questionRecord : questionRecordList) {
            TbExamUserAnswerDTO userAnswerDTO = userAnswerDTOMap.get(questionRecord.getId());
            questionRecord.setAnswerResult(CommonConstants.NO);
            if (ObjectUtil.isNotEmpty(userAnswerDTO)) {
                questionRecord.setUserAnswer(userAnswerDTO.getExamAnswer());
                if (StrUtil.equals(userAnswerDTO.getExamAnswer(), questionRecord.getExamAnswer())) {
                    questionRecord.setAnswerResult(CommonConstants.YES);
                    // 每道题得分
                    score += examProperties.getQuestionScore();
                }
            }
        }
        record.setStatus(CommonConstants.YES)
                .setExamScore(score)
                .setExamDate(LocalDate.now())
                .setResult(score > examProperties.getQualifiedScore() ? CommonConstants.YES : CommonConstants.NO);
        updateById(record);
        // 个人考试结果
        TbExamResult result = examResultService.getOne(Wrappers.lambdaQuery(TbExamResult.class).eq(TbExamResult::getUserId, AuthUtil.getCurrentUserId()));
        if (ObjectUtil.isNotEmpty(result)) {
            result = new TbExamResult();
        }
        LocalDateTime offset = LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), examProperties.getCertificateMonth(), ChronoUnit.MONTHS);
        result.setExamScore(score)
                .setUserId(AuthUtil.getCurrentUserId())
                .setExamDate(record.getExamDate())
                .setCertificateExpireDate(LocalDate.of(offset.getYear(), offset.getMonth(), offset.getDayOfMonth()));
        examResultService.saveOrUpdate(result);
        TbExamEndResultDTO resultDTO = new TbExamEndResultDTO();
        resultDTO.setResult(record.getResult());
        if (StrUtil.equals(record.getResult(), CommonConstants.YES)) {
            resultDTO.setCertificateStartDate(result.getExamDate())
                    .setCertificateExpireDate(result.getCertificateExpireDate());
        }
        return resultDTO;
    }
}
