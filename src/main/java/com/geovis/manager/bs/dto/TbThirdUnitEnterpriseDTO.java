package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbThirdUnit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 第三方单位对象企业端
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitDTO", description = "第三方单位对象企业端")
public class TbThirdUnitEnterpriseDTO extends TbThirdUnit {

    @ApiModelProperty("培训状态：1-已培训 0-未培训")
    private String trainStatus;

    @ApiModelProperty("培训时间")
    private LocalDate trainTime;

    @ApiModelProperty("培训内容")
    private String trainContent;

}
