package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbThirdUnit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 第三方单位对象园区端
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitParkDTO", description = "第三方单位对象园区端")
public class TbThirdUnitParkDTO extends TbThirdUnit {

    @ApiModelProperty("企业名称，多个逗号隔开")
    private String enterpriseNames;

}
