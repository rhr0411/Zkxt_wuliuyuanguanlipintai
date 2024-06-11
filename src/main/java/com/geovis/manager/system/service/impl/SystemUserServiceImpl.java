package com.geovis.manager.system.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.auth.context.AuthUser;
import com.geovis.common.auth.service.IAuthService;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.*;
import com.geovis.manager.system.entity.SystemRole;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.entity.SystemUserRole;
import com.geovis.manager.system.mapper.SystemRoleMapper;
import com.geovis.manager.system.mapper.SystemUserMapper;
import com.geovis.manager.system.service.ISystemUserRoleService;
import com.geovis.manager.system.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService, IAuthService {

    private final ISystemUserRoleService systemUserRoleService;

    private final SystemRoleMapper systemRoleMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginUserDTO getByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public LoginUserInfoDTO getByUserId(String userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeByIdList(Set<String> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }

        // 1、删除角色-用户关联表
        systemUserRoleService.remove(Wrappers.lambdaUpdate(SystemUserRole.class).in(SystemUserRole::getUserId, idList));

        // 2、删除用户
        removeByIds(idList);
    }

    @Override
    public PageResult<SystemUserDTO> listPage(PageParam<SystemUserQueryDTO> pageParam) {
        SystemUserQueryDTO queryDTO = pageParam.getQuery();
        IPage<SystemUserDTO> page = pageParam.buildPage();
        QueryWrapper<Object> wrapper = Wrappers.query();
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getUserName()), "a.user_name", queryDTO.getUserName())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getNickName()), "a.nick_name", queryDTO.getNickName())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getRealName()), "a.real_name", queryDTO.getRealName())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getPhone()), "a.phone", queryDTO.getPhone())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getSex()), "a.sex", queryDTO.getSex())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getDeptId()), "a.dept_id", queryDTO.getDeptId())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getType()), "a.type", queryDTO.getType())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getJob()), "a.job", queryDTO.getJob())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getDutyStatus()), "a.duty_status", queryDTO.getDutyStatus())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getRelatedId()), "a.related_id", queryDTO.getRelatedId());
        }
        baseMapper.selectByPage(page, wrapper);
        return new PageResult<SystemUserDTO>(page);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void assignRole(SystemUserAssignRoleDTO assignRoleDTO) {

        if (ObjectUtil.isEmpty(assignRoleDTO) || ObjectUtil.isEmpty(assignRoleDTO.getId())) {
            return;
        }
        // 1、删除用户角色中间表
        systemUserRoleService.remove(Wrappers.lambdaUpdate(SystemUserRole.class).in(SystemUserRole::getUserId, assignRoleDTO.getId()));

        if (ObjectUtil.isEmpty(assignRoleDTO.getRoleIdList())) {
            return;
        }
        // 2、要保存用户角色中间表的列表
        List<SystemUserRole> systemUserRoleList = ListUtil.list(false);
        assignRoleDTO.getRoleIdList().forEach(roleId -> {
            SystemUserRole systemUserRole = new SystemUserRole()
                    .setUserId(assignRoleDTO.getId())
                    .setRoleId(roleId);
            systemUserRoleList.add(systemUserRole);
        });
        systemUserRoleService.saveBatch(systemUserRoleList);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void register(SystemUserRegisterDTO registerDTO) {
        SystemUser systemUser = BeanUtil.toBean(registerDTO, SystemUser.class);
        systemUser.setDataStatus(CommonConstants.YES);
        systemUser.setAvatar(null);
        // 默认密码
        systemUser.setUserPassword(passwordEncoder.encode("123456"));
        save(systemUser);

        // 默认角色
        SystemRole systemRole = systemRoleMapper.selectOne(Wrappers.lambdaQuery(SystemRole.class).eq(SystemRole::getEnName, "ordinary"));
        if (ObjectUtil.isEmpty(systemRole)) {
            return;
        }
        SystemUserAssignRoleDTO assignRoleDTO = new SystemUserAssignRoleDTO();
        assignRoleDTO.setId(systemUser.getId())
                .setRoleIdList(CollUtil.list(false, systemRole.getId()));
        assignRole(assignRoleDTO);
    }

    @Override
    public AuthUser getByUserName(String username) {
        LoginUserDTO LoginUserDTO = this.getByUsername(username);
        if(ObjectUtil.isEmpty(LoginUserDTO)){
            return null;
        }
        return BeanUtil.copyProperties(LoginUserDTO, AuthUser.class);
    }
}
