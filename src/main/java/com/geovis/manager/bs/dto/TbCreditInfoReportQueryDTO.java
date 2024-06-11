package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 信用信息报送查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCreditInfoReportQueryDTO", description = "信用信息报送查询")
public class TbCreditInfoReportQueryDTO implements Serializable {

    @ApiModelProperty("信用信息类型：1-行政许可 0-行政处罚")
    private String type;

    @ApiModelProperty("关联企业")
    private String enterpriseId;

}
