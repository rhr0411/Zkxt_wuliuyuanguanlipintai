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
 * 角色分配资源DTO
 * </p>
 *
 * @author 王响
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "SystemRoleAssignResourceDTO", description = "角色分配资源DTO")
public class SystemRoleAssignResourceDTO implements Serializable {

    @ApiModelProperty(value = "角色ID", required = true)
    @NotBlank(message = "角色ID不能为空")
    private String id;

    @ApiModelProperty(value = "资源idList")
    private List<String> resourceIdList;

}
