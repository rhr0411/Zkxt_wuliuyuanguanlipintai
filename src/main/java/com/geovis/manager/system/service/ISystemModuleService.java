package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.system.dto.SystemModuleDTO;
import com.geovis.manager.system.entity.SystemModule;
import com.geovis.manager.system.entity.SystemResources;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统模块 服务类
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
public interface ISystemModuleService extends IService<SystemModule> {


    /**
     * 获取所有的模块
     *
     * @return 返回资源列表
     */
    List<SystemModuleDTO> selectAll();


    /**
     * 设置资源信息
     * @param dto 模块对象
     */
    void settingResources(SystemModuleDTO dto);


    /**
     * 根据模块id获取资源
     * @param id 模块id
     * @return 资源数组
     */
    Set<SystemResources> getResources(String id);

}
