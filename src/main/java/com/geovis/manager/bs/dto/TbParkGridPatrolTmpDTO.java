package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbParkGridPatrolTmp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 园区网格_巡检设置对象
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolTmpDTO", description = "园区网格_巡检设置对象")
public class TbParkGridPatrolTmpDTO extends TbParkGridPatrolTmp {

    @ApiModelProperty("队长")
    private TbParkGridPatrolTmpUserDTO teamLeader;

    @ApiModelProperty("成员列表")
    private List<TbParkGridPatrolTmpUserDTO> userList;

}
