package com.geovis.manager.bs.service;

import com.geovis.manager.bs.entity.TbExamQuestionRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 考生答题记录 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
public interface ITbExamQuestionRecordService extends IService<TbExamQuestionRecord> {

    /**
     * 生成试题
     * @param examRecordId
     * @param currentUserId
     * @return
     */
    List<TbExamQuestionRecord> genQuestionList(String examRecordId, String currentUserId);
}
