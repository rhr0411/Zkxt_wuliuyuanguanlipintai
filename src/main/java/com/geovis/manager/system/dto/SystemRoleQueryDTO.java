package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * <p>
 * 角色信息查询DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SystemRoleQueryDTO", description = "角色信息查询DTO")
public class SystemRoleQueryDTO implements Serializable {

    @ApiModelProperty(value = "角色名称")
    @Length(max = 128, message = "角色名称最长不能超过{128}个字符")
    private String name;

    @ApiModelProperty(value = "角色英文名称")
    @Length(max = 128, message = "角色英文名称最长不能超过{128}个字符")
    private String enName;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;
}
