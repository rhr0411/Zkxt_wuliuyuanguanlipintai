package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.bs.entity.TbResponsibility;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 责任清单管理 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface TbResponsibilityMapper extends BaseMapper<TbResponsibility> {

    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     */
    IPage<TbResponsibility> selectResponsibilityPage(@Param("page") IPage<TbResponsibility> page, @Param(Constants.WRAPPER) QueryWrapper<Object> wrapper);
}
