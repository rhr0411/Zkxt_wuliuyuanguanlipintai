package com.geovis.manager.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户分配角色DTO
 * </p>
 *
 * @author 王响
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "SystemUserAssignRoleDTO", description = "用户分配角色DTO")
public class SystemUserAssignRoleDTO implements Serializable {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotBlank(message = "用户ID不能为空")
    private String id;

    @ApiModelProperty(value = "角色idList")
    private List<String> roleIdList;

}
