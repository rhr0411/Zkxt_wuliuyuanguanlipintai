package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.system.dto.SystemResourcesDTO;
import com.geovis.manager.system.dto.SystemResourcesQueryDTO;
import com.geovis.manager.system.entity.SystemResources;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统资源表 服务类
 * </p>
 *
 * @author 王响
 * @since 2020-07-27
 */
public interface ISystemResourcesService extends IService<SystemResources> {

    /**
     * 获取所有的菜单角色资源
     *
     * @param queryDTO
     * @return
     */
    List<SystemResourcesDTO> list(SystemResourcesQueryDTO queryDTO);

    /**
     * 批量删除
     *
     * @param idList 批量删除的id数组
     */
    void removeByIdList(Set<String> idList);

}
