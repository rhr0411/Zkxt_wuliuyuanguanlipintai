package com.geovis.manager.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户信息表注册DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemUserRegisterDTO", description = "用户信息表注册DTO")
public class SystemUserRegisterDTO extends SystemUserSaveDTO {

    @ApiModelProperty(value = "主键id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;

}
