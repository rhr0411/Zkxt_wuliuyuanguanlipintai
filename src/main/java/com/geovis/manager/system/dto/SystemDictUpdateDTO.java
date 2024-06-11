package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 字典信息修改DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDictUpdateDTO", description = "字典信息修改DTO")
public class SystemDictUpdateDTO extends SystemDictSaveDTO {

    @ApiModelProperty(value = "id主键", required = true)
    @NotBlank(message = "id主键不能为空")
    private String id;

    @ApiModelProperty(value = "数据状态（1-启用，0-禁用）")
    @NotBlank(message = "数据状态（1-启用，0-禁用）不能为空")
    @Length(max = 1, message = "数据状态（1-启用，0-禁用）最长不能超过{1}个字符")
    private String dataStatus;
}
