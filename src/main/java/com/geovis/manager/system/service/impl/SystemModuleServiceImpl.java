package com.geovis.manager.system.service.impl;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.dto.SystemModuleDTO;
import com.geovis.manager.system.entity.SystemModule;
import com.geovis.manager.system.entity.SystemModuleResources;
import com.geovis.manager.system.entity.SystemResources;
import com.geovis.manager.system.mapper.SystemModuleMapper;
import com.geovis.manager.system.service.ISystemModuleResourcesService;
import com.geovis.manager.system.service.ISystemModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统模块 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystemModuleServiceImpl extends ServiceImpl<SystemModuleMapper, SystemModule> implements ISystemModuleService {

    private final ISystemModuleResourcesService systemModuleResourcesService;

    @Override
    public List<SystemModuleDTO> selectAll() {
        return baseMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void settingResources(SystemModuleDTO dto) {
        if (ObjectUtil.isEmpty(dto) || ObjectUtil.isEmpty(dto.getId())) {
            return;
        }

        // 1、删除角色资源中间表
        systemModuleResourcesService.remove(new UpdateWrapper<SystemModuleResources>().lambda().eq(SystemModuleResources::getModuleId, dto.getId()));

        if (ObjectUtil.isEmpty(dto.getResourceIdList())) {
            return;
        }

        // 2、添加角色资源中间表的记录
        List<SystemModuleResources> systemModuleResourcesList = ListUtil.list(false);
        dto.getResourceIdList().forEach(item -> {
            SystemModuleResources systemModuleResources = new SystemModuleResources()
                    .setModuleId(dto.getId())
                    .setResourcesId(item);
            systemModuleResourcesList.add(systemModuleResources);
        });
        systemModuleResourcesService.saveBatch(systemModuleResourcesList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Set<SystemResources> getResources(String id) {
        return baseMapper.getResources(id);
    }
}
