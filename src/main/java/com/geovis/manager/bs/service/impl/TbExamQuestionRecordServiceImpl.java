package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.bs.entity.TbExamQuestion;
import com.geovis.manager.bs.entity.TbExamQuestionRecord;
import com.geovis.manager.bs.mapper.TbExamQuestionRecordMapper;
import com.geovis.manager.bs.props.ExamProperties;
import com.geovis.manager.bs.service.ITbExamQuestionRecordService;
import com.geovis.manager.bs.service.ITbExamQuestionService;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 考生答题记录 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbExamQuestionRecordServiceImpl extends ServiceImpl<TbExamQuestionRecordMapper, TbExamQuestionRecord> implements ITbExamQuestionRecordService {

    private final ISystemUserService systemUserService;

    private final ITbExamQuestionService examQuestionService;

    private final ExamProperties examProperties;

    @Override
    public List<TbExamQuestionRecord> genQuestionList(String examRecordId, String userId) {
        // 查询用户工种
        SystemUser systemUser = systemUserService.getById(userId);
        // 根据工种查询试题列表
        List<TbExamQuestion> questionList = examQuestionService.list(Wrappers.lambdaQuery(TbExamQuestion.class)
                .exists("select 1 from tb_exam_question_work_type a where a.exam_question_id = tb_exam_question.id and a.worker_type = '" + systemUser.getWorkerType() + "'"));
        int requireCount = examProperties.getFullScore() / examProperties.getQualifiedScore();
        Assert.isFalse(requireCount > questionList.size(), "题库中试题量不足" + requireCount + "，请先添加试题");
        List<TbExamQuestion> randomEleList = RandomUtil.randomEleList(questionList, requireCount);
        List<TbExamQuestionRecord> list = CollUtil.list(false);
        for (int i = 0; i < randomEleList.size(); i++) {
            TbExamQuestionRecord record = BeanUtil.copyProperties(randomEleList.get(i), TbExamQuestionRecord.class);
            record.setSortIndex(i).setUserId(userId).setExamRecordId(examRecordId);
            list.add(record);
        }
        saveBatch(list);
        return list;
    }

}
