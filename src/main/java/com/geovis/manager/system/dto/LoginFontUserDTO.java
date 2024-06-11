package com.geovis.manager.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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
public class LoginFontUserDTO implements Serializable {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "设备标识", required = true)
    @NotEmpty(message = "设备标识不能为空")
    private String eqpFlag;

    @ApiModelProperty(value = "手机验证码，新设备时为必填项")
    private String phoneCode;

}
