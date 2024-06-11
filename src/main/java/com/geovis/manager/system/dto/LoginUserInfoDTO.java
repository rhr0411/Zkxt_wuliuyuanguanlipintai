package com.geovis.manager.system.dto;


import com.geovis.manager.system.entity.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 用户信息DTO
 * </p>
 *
 * @author 王响
 * @since 2020-10-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "LoginUserInfoDTO", description = "用户信息、角色信息、权限信息DTO")
public class LoginUserInfoDTO implements Serializable {

    @ApiModelProperty(value = "用户主键id")
    private String userId;

    @ApiModelProperty(value = "用户名Id")
    private SystemUser systemUser;

    @ApiModelProperty(value = "机构名")
    private String deptName;

    @ApiModelProperty(value = "角色集合")
    private Set<String> roleList;

    @ApiModelProperty(value = "权限集合")
    private Set<String> permissionList;

}
