package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 第三方单位查询对象园区端
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitParkQueryDTO", description = "第三方单位查询对象园区端")
public class TbThirdUnitParkQueryDTO implements Serializable {

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("资质等级")
    private String qualificationLevel;

    @ApiModelProperty("状态：1-待审核 2-审核通过 3-审核驳回")
    private String status;

    @ApiModelProperty("资质到期时间-开始时间")
    private LocalDate expireDateStart;

    @ApiModelProperty("资质到期时间-结束时间")
    private LocalDate expireDateEnd;

}
