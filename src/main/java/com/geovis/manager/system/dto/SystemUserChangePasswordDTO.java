package com.geovis.manager.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 修改密码DTO
 * </p>
 *
 * @author 王响
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "SystemUserChangePasswordDTO", description = "修改密码DTO")
public class SystemUserChangePasswordDTO implements Serializable {

    @ApiModelProperty(value = "旧密码", required = true)
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
