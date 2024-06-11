package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 园区网格
 * </p>
 *
 * @author zengds
 * @since 2024-04-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_park_grid")
@ApiModel(value = "TbParkGrid", description = "园区网格")
public class TbParkGrid extends BaseEntity {

    @ApiModelProperty("网格名称")
    private String name;

    @ApiModelProperty("坐标点区域")
    private String coordinatePoints;


}
