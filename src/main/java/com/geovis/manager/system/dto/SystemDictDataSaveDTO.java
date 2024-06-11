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
 * 字典数据信息保存DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDictDataSaveDTO", description = "字典数据信息保存DTO")
public class SystemDictDataSaveDTO implements Serializable {

    @ApiModelProperty(value = "字典id", required = true)
    @NotNull(message = "字典id不能为空")
    private String dictId;

    @ApiModelProperty(value = "字典数据key", required = true)
    @NotBlank(message = "字典数据key不能为空")
    @Length(max = 32, message = "字典数据key最长不能超过{32}个字符")
    private String dataKey;

    @ApiModelProperty(value = "字典数据value", required = true)
    @NotBlank(message = "字典数据value不能为空")
    @Length(max = 255, message = "字典数据value最长不能超过{255}个字符")
    private String dataValue;

    @ApiModelProperty(value = "上级字典数据key（构建树状使用）")
    @Length(max = 32, message = "上级字典数据key（构建树状使用）最长不能超过{32}个字符")
    private String dataParentKey;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    @Length(max = 255, message = "备注最长不能超过{255}个字符")
    private String remark;
}
