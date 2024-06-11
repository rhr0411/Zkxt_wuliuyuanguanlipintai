package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.*;
import com.geovis.manager.system.entity.SystemUser;

import java.util.Set;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
public interface ISystemUserService extends IService<SystemUser> {

    /**
     * 根据用户ID查询用户相关信息 (spring security登录校验)
     *
     * @param username 用户名称
     * @return SystemUserDTO 登录用户信息
     */
    LoginUserDTO getByUsername(String username);

    /**
     * 根据用户ID查询用户相关信息
     *
     * @param userId 用户id
     * @return 登录用户详细信息
     */
    LoginUserInfoDTO getByUserId(String userId);

    /**
     * 删除
     *
     * @param idList 批量删除的id数组
     */
    void removeByIdList(Set<String> idList);

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<SystemUserDTO> listPage(PageParam<SystemUserQueryDTO> pageParam);

    /**
     * 分配角色
     *
     * @param assignRoleDTO 用户分配的角色数组
     */
    void assignRole(SystemUserAssignRoleDTO assignRoleDTO);


    /**
     * 添加用户、并给以普通用户的角色
     * @param registerDTO
     */
    void register(SystemUserRegisterDTO registerDTO);

}
