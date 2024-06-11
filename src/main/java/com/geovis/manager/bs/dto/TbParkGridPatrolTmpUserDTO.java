package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbParkGridPatrolTmpUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 园区网格_巡查模版_成员表
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolTmpUserDTO", description = "园区网格_巡查模版_成员表")
public class TbParkGridPatrolTmpUserDTO extends TbParkGridPatrolTmpUser {

    @ApiModelProperty("成员名称")
    private String userName;


}
