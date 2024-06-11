package com.geovis.manager.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemUserRole;
import com.geovis.manager.system.mapper.SystemUserRoleMapper;
import com.geovis.manager.system.service.ISystemUserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {

}
