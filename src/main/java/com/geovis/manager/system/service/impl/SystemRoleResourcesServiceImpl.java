package com.geovis.manager.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemRoleResources;
import com.geovis.manager.system.mapper.SystemRoleResourcesMapper;
import com.geovis.manager.system.service.ISystemRoleResourcesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色资源表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemRoleResourcesServiceImpl extends ServiceImpl<SystemRoleResourcesMapper, SystemRoleResources> implements ISystemRoleResourcesService {

}
