package com.geovis.manager.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.dto.SystemRoleAssignResourceDTO;
import com.geovis.manager.system.entity.SystemRole;
import com.geovis.manager.system.entity.SystemRoleResources;
import com.geovis.manager.system.entity.SystemUserRole;
import com.geovis.manager.system.mapper.SystemRoleMapper;
import com.geovis.manager.system.service.ISystemRoleResourcesService;
import com.geovis.manager.system.service.ISystemRoleService;
import com.geovis.manager.system.service.ISystemUserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {

    private final ISystemRoleResourcesService systemRoleResourcesService;

    private final ISystemUserRoleService systemUserRoleService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeByIdList(Set<String> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        // 1、删除角色资源中间表
        systemRoleResourcesService.remove(Wrappers.lambdaUpdate(SystemRoleResources.class).in(SystemRoleResources::getRoleId, idList));

        // 2、删除角色-用户关联表
        systemUserRoleService.remove(Wrappers.lambdaUpdate(SystemUserRole.class).in(SystemUserRole::getRoleId, idList));

        // 3、删除角色
        removeByIds(idList);

        // 4、递归删除孩子节点
        List<SystemRole> list = baseMapper.selectList(Wrappers.lambdaQuery(SystemRole.class).select(SystemRole::getId).in(SystemRole::getParentId, idList));
        Set<String> childrenIdList = CollUtil.set(false);
        list.forEach(item -> childrenIdList.add(item.getId()));
        removeByIdList(childrenIdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assignResource(SystemRoleAssignResourceDTO assignResourceDTO) {
        if (ObjectUtil.isEmpty(assignResourceDTO) || ObjectUtil.isEmpty(assignResourceDTO.getId())) {
            return;
        }

        // 1、删除角色资源中间表
        systemRoleResourcesService.remove(Wrappers.lambdaUpdate(SystemRoleResources.class).eq(SystemRoleResources::getRoleId, assignResourceDTO.getId()));

        if (ObjectUtil.isEmpty(assignResourceDTO.getResourceIdList())) {
            return;
        }

        // 2、添加角色资源中间表的记录
        List<SystemRoleResources> systemRoleResourcesList = ListUtil.list(false);
        assignResourceDTO.getResourceIdList().forEach(resourceId -> {
            SystemRoleResources systemRoleResources = new SystemRoleResources()
                    .setRoleId(assignResourceDTO.getId())
                    .setResourcesId(resourceId);
            systemRoleResourcesList.add(systemRoleResources);
        });
        systemRoleResourcesService.saveBatch(systemRoleResourcesList);
    }
}
