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
 * 第三方单位查询对象企业端
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitEnterpriseQueryDTO", description = "第三方单位查询对象企业端")
public class TbThirdUnitEnterpriseQueryDTO implements Serializable {

    @ApiModelProperty("培训状态：1-已培训 0-未培训")
    private String trainStatus;

    @ApiModelProperty("单位类别")
    private String type;

    @ApiModelProperty("状态：1-待审核 2-审核通过 3-审核驳回")
    private String status;

}
