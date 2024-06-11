package com.geovis.manager.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 登录用户DTO
 * </p>
 *
 * @author 王响
 * @since 2020-10-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "LoginUserDTO", description = "登录用户信息、角色信息、权限信息DTO")
public class LoginUserDTO implements Serializable {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "机构id")
    private String deptId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "数据状态（1-未删除，0-删除）")
    private Integer dataStatus;

    @ApiModelProperty(value = "角色集合")
    private Set<String> roleList;

    @ApiModelProperty(value = "权限集合")
    private Set<String> permissionList;

}
