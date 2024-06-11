package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.bs.dto.TbExamQuestionDTO;
import com.geovis.manager.bs.entity.TbExamQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 考试题库 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
public interface TbExamQuestionMapper extends BaseMapper<TbExamQuestion> {

    /**
     * 分页查询
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<TbExamQuestionDTO> selectDataList(@Param("page") IPage<TbExamQuestionDTO> page, @Param(Constants.WRAPPER) QueryWrapper<Object> queryWrapper);

    /**
     * 列表查询
     *
     * @param queryWrapper
     * @return
     */
    List<TbExamQuestionDTO> selectDataList(@Param(Constants.WRAPPER) QueryWrapper<Object> queryWrapper);
}
