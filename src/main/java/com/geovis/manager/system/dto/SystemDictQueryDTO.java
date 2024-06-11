package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * <p>
 * 字典查询DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SystemDictQueryDTO", description = "字典查询DTO")
public class SystemDictQueryDTO implements Serializable {

    @ApiModelProperty(value = "字典码")
    @Length(max = 128, message = "字典码最长不能超过{128}个字符")
    private String code;

    @ApiModelProperty(value = "字典名称")
    @Length(max = 255, message = "字典名称最长不能超过{255}个字符")
    private String name;

    @ApiModelProperty(value = "字典类型(1-系统字典，2-业务字典)", required = true)
    @Length(max = 1, message = "字典类型(1-系统字典，2-业务字典)最长不能超过{1}个字符")
    private String type;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;
}
