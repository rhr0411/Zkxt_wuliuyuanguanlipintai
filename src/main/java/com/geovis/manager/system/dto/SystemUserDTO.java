package com.geovis.manager.system.dto;


import com.geovis.manager.system.entity.SystemRole;
import com.geovis.manager.system.entity.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * <p>
 * 用户信息VO
 * </p>
 *
 * @author 曾德实
 * @since 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemUserDTO对象", description = "用户信息表DTO")
public class SystemUserDTO extends SystemUser {

    @ApiModelProperty(value = "角色List")
    private List<SystemRole> roleList;

    @ApiModelProperty(value = "机构名")
    private String deptName;
}
