package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.TbExamEndAnswerDTO;
import com.geovis.manager.bs.dto.TbExamEndResultDTO;
import com.geovis.manager.bs.entity.TbExamQuestionRecord;
import com.geovis.manager.bs.entity.TbExamRecord;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
public interface ITbExamRecordService extends IService<TbExamRecord> {

    /**
     * 开始考试
     *
     * @param currentUserId
     * @return
     */
    List<TbExamQuestionRecord> beginExam(String currentUserId);

    /**
     * 结束考试
     *
     * @param dto
     * @return
     */
    TbExamEndResultDTO endExam(TbExamEndAnswerDTO dto);
}
