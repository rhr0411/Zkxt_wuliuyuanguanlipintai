package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 角色保存DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemRoleSaveDTO", description = "角色信息保存DTO")
public class SystemRoleSaveDTO implements Serializable {

    @ApiModelProperty(value = "父角色id")
    private String parentId;

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 128, message = "角色名称最长不能超过{128}个字符")
    private String name;

    @ApiModelProperty(value = "角色名称(英文)", required = true)
    @NotBlank(message = "角色名称(英文)不能为空")
    @Length(max = 128, message = "角色名称(英文)最长不能超过{128}个字符")
    private String enName;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序", required = true)
    private Integer idx;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;
}
