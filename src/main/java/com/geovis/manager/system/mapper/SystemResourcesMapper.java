package com.geovis.manager.system.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.system.dto.SystemResourcesDTO;
import com.geovis.manager.system.entity.SystemResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统资源表 Mapper 接口
 * </p>
 *
 * @author 王响
 * @since 2020-07-27
 */
public interface SystemResourcesMapper extends BaseMapper<SystemResources> {

    /**
     * 获取所有的菜单
     *
     * @return 返回资源列表
     */
    List<SystemResourcesDTO> selectByList(@Param(Constants.WRAPPER) Wrapper<Object> wrapper);


}
