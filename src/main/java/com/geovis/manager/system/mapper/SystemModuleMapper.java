package com.geovis.manager.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geovis.manager.system.dto.SystemModuleDTO;
import com.geovis.manager.system.entity.SystemModule;
import com.geovis.manager.system.entity.SystemResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统模块 Mapper 接口
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
public interface SystemModuleMapper extends BaseMapper<SystemModule> {

    /**
     * 获取所有的模块
     *
     * @return 返回资源列表
     */
    List<SystemModuleDTO> selectAll();


    /**
     * 根据模块 获取顶级资源
     *
     * @param id 模块id
     * @return 返回资源
     */
    Set<SystemResources> getResources(@Param("id") String id);



}
