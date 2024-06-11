package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbEqpCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 装置开停车和大检修管理保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpCheckSaveOrUpdateDTO", description = "装置开停车和大检修管理保存或更新")
public class TbEqpCheckDTO extends TbEqpCheck {

    @ApiModelProperty("预警列表")
    private List<TbEqpCheck> warnList;

}
