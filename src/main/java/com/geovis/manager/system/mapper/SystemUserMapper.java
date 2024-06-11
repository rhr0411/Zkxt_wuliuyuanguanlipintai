package com.geovis.manager.system.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.system.dto.LoginUserDTO;
import com.geovis.manager.system.dto.LoginUserInfoDTO;
import com.geovis.manager.system.dto.SystemUserDTO;
import com.geovis.manager.system.entity.SystemUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 王响
 * @since 2020-07-15
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    /**
     * 根据用户名称查询用户信息、角色信息、权限信息
     *
     * @param username 用户名称
     * @return SystemUserDTO 登录用户的信息
     */
    LoginUserDTO selectByUsername(@Param("username") String username);

    /**
     * 根据用户名称查询用户信息、角色信息、权限信息
     *
     * @param userId 用户id
     * @return LoginUserInfoDTO 登录用户的详细信息
     */
    LoginUserInfoDTO selectByUserId(@Param("userId") String userId);



    /**
     * 分页查询
     *
     * @param page    分页参数
     * @param wrapper
     * @return 返回用户分页
     */
    IPage<SystemUserDTO> selectByPage(@Param("page") IPage<SystemUserDTO> page, @Param(Constants.WRAPPER) Wrapper<Object> wrapper);

}
