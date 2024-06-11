package com.geovis.manager.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.dto.SystemResourcesDTO;
import com.geovis.manager.system.dto.SystemResourcesQueryDTO;
import com.geovis.manager.system.entity.SystemResources;
import com.geovis.manager.system.entity.SystemRoleResources;
import com.geovis.manager.system.mapper.SystemResourcesMapper;
import com.geovis.manager.system.service.ISystemResourcesService;
import com.geovis.manager.system.service.ISystemRoleResourcesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-07-27
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemResourcesServiceImpl extends ServiceImpl<SystemResourcesMapper, SystemResources> implements ISystemResourcesService {

    private final ISystemRoleResourcesService systemRoleResourcesService;

    @Override
    public List<SystemResourcesDTO> list(SystemResourcesQueryDTO queryDTO) {
        QueryWrapper<Object> wrapper = Wrappers.query();
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getRoleId()), "c.id", queryDTO.getRoleId());
        }
        List<SystemResourcesDTO> list = baseMapper.selectByList(wrapper);

        // 填充角色，如果子资源已经有了该角色；那么父资源自动填充
        Map<String, SystemResourcesDTO> map = IterUtil.toMap(list, SystemResourcesDTO::getId);
        list.stream().filter(systemResourcesDTO -> ObjectUtil.isNotEmpty(systemResourcesDTO.getRoleList())).forEach(systemResourcesDTO -> {
            SystemResourcesDTO dto = map.get(systemResourcesDTO.getParentId());
            while (ObjectUtil.isNotEmpty(dto)) {
                Set<String> roleSet = dto.getRoleList();
                if (ObjectUtil.isEmpty(roleSet)) {
                    roleSet = CollUtil.set(false);
                }
                roleSet.addAll(systemResourcesDTO.getRoleList());
                dto = map.get(dto.getParentId());
            }
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeByIdList(Set<String> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        //1、刪除本身
        removeByIds(idList);


        // 2、删除角色资源中间表
        systemRoleResourcesService.remove(Wrappers.lambdaUpdate(SystemRoleResources.class).in(SystemRoleResources::getResourcesId, idList));


        //4、刪除孩子节点
        List<SystemResources> list = baseMapper.selectList(Wrappers.lambdaQuery(SystemResources.class).select(SystemResources::getId).in(SystemResources::getParentId, idList));
        Set<String> childrenIdList = CollUtil.set(false);
        list.forEach(systemResources -> childrenIdList.add(systemResources.getId()));
        removeByIdList(childrenIdList);
    }
}
