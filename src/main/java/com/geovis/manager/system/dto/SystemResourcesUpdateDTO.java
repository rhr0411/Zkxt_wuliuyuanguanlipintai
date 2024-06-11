package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 资源信息修改DTO
 * </p>
 *
 * @author 王响
 * @since 2021-11-09
 */

@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemResourcesUpdateDTO", description = "资源信息修改DTO")
public class SystemResourcesUpdateDTO extends SystemResourcesSaveDTO {

    @ApiModelProperty(value = "id主键", required = true)
    @NotBlank(message = "id主键不能为空")
    private String id;

}
