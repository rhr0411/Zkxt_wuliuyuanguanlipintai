package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.bs.dto.TbThirdUnitEnterpriseDTO;
import com.geovis.manager.bs.dto.TbThirdUnitParkDTO;
import com.geovis.manager.bs.entity.TbThirdUnit;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 第三方单位 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
public interface TbThirdUnitMapper extends BaseMapper<TbThirdUnit> {

    /**
     * 企业端-分页查询
     *
     * @param page
     * @param wrapper
     */
    IPage<TbThirdUnitEnterpriseDTO> getEnterprisePage(@Param("page") IPage<TbThirdUnitEnterpriseDTO> page, @Param(Constants.WRAPPER) QueryWrapper<Object> wrapper);

    /**
     * 园区端-分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<TbThirdUnitParkDTO> getParkPage(@Param("page") IPage<TbThirdUnitParkDTO> page, @Param(Constants.WRAPPER) QueryWrapper<Object> wrapper);
}
