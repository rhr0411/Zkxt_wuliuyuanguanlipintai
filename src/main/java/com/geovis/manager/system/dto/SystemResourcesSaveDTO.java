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
 * 资源信息保存DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemResourcesSaveDTO", description = "资源信息保存DTO")
public class SystemResourcesSaveDTO implements Serializable {

    @ApiModelProperty(value = "父级资源id", required = true)
    @NotNull(message = "父级资源id不能为空")
    private String parentId;

    @ApiModelProperty(value = "资源名称", required = true)
    @NotBlank(message = "资源名称不能为空")
    @Length(max = 32, message = "资源名称最长不能超过{32}个字符")
    private String name;

    @ApiModelProperty(value = "授权标识")
    @Length(max = 128, message = "授权标识最长不能超过{128}个字符")
    private String enName;

    @ApiModelProperty(value = "资源类型：1-目录， 2-菜单 ，3-按钮", required = true)
    @NotBlank(message = "资源类型不能为空")
    private String type;

    @ApiModelProperty(value = "访问组件名称")
    @Length(max = 128, message = "访问组件名称最长不能超过{128}个字符")
    private String componentName;

    @ApiModelProperty(value = "访问路径")
    @Length(max = 128, message = "访问路径最长不能超过{128}个字符")
    private String url;

    @ApiModelProperty(value = "图标")
    @Length(max = 32, message = "图标最长不能超过{32}个字符")
    private String icon;

    @ApiModelProperty(value = "菜单固定标签")
    private Boolean affix;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;
}
