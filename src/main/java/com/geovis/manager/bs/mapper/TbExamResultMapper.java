package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.bs.dto.TbExamResultDTO;
import com.geovis.manager.bs.entity.TbExamResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 成绩管理和证书发放 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
public interface TbExamResultMapper extends BaseMapper<TbExamResult> {

    /**
     * 分页查询
     *
     * @param page
     * @param objectQueryWrapper
     */
    IPage<TbExamResultDTO> selectDataList(@Param("page") IPage<TbExamResultDTO> page, @Param(Constants.WRAPPER) QueryWrapper<Object> objectQueryWrapper);
}
