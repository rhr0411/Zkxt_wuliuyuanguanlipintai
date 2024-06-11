package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbExamQuestionDTO;
import com.geovis.manager.bs.dto.TbExamQuestionQueryDTO;
import com.geovis.manager.bs.dto.TbExamQuestionSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbExamQuestion;

import java.util.List;

/**
 * <p>
 * 考试题库 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
public interface ITbExamQuestionService extends IService<TbExamQuestion> {

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<TbExamQuestionDTO> getPage(PageParam<TbExamQuestionQueryDTO> pageParam);

    /**
     * 列表查询
     *
     * @param queryDTO
     * @return
     */
    List<TbExamQuestionDTO> getList(TbExamQuestionQueryDTO queryDTO);

    /**
     * 保存或更新
     *
     * @param dto
     */
    void saveOrUpdateEntity(TbExamQuestionSaveOrUpdateDTO dto);
}
