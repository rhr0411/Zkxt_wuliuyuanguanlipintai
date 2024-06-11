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
 * 机构保存DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDeptSaveDTO", description = "机构保存DTO")
public class SystemDeptSaveDTO implements Serializable {

    @ApiModelProperty(value = "父主键", required = true)
    @NotBlank(message = "父主键不能为空")
    private String parentId;

    @ApiModelProperty(value = "机构名")
    @NotBlank(message = "机构名不能为空")
    @Length(max = 128, message = "机构名最长不能超过{128}个字符")
    private String deptName;

    @ApiModelProperty(value = "机构全称")
    @NotBlank(message = "机构全称不能为空")
    @Length(max = 128, message = "机构全称最长不能超过{128}个字符")
    private String fullName;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;

}
