package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.system.dto.SystemRoleAssignResourceDTO;
import com.geovis.manager.system.entity.SystemRole;

import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
public interface ISystemRoleService extends IService<SystemRole> {

    /**
     * 删除
     *
     * @param idList 批量删除的id数组
     */
    void removeByIdList(Set<String> idList);

    /**
     * 分配资源
     *
     * @param assignResourceDTO
     */
    void assignResource(SystemRoleAssignResourceDTO assignResourceDTO);

}
