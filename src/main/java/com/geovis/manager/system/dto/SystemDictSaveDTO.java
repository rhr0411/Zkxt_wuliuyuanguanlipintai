package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 字典信息保存DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDictSaveDTO", description = "字典信息保存DTO")
public class SystemDictSaveDTO implements Serializable {

    @ApiModelProperty(value = "字典码", required = true)
    @NotBlank(message = "字典码不能为空")
    @Length(max = 128, message = "字典码最长不能超过{128}个字符")
    private String code;

    @ApiModelProperty(value = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    @Length(max = 255, message = "字典名称最长不能超过{255}个字符")
    private String name;

    @ApiModelProperty(value = "字典类型(1-系统字典，2-业务字典)", required = true)
    @NotBlank(message = "字典名称不能为空")
    @Length(max = 1, message = "字典类型(1-系统字典，2-业务字典)最长不能超过{1}个字符")
    private String type;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;
}
