package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 机构修改DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemDeptUpdateDTO", description = "机构修改DTO")
public class SystemDeptUpdateDTO extends SystemDeptSaveDTO {

    @ApiModelProperty(value = "id主键", required = true)
    @NotBlank(message = "id主键不能为空")
    private String id;

}
