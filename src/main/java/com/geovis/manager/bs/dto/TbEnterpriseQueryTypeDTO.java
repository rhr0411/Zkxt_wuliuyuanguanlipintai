package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 企业信息查询
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseQueryTypeDTO", description = "企业信息类型查询")
public class TbEnterpriseQueryTypeDTO implements Serializable {


    @ApiModelProperty("监管分类：1-一般监督 2-终点关注 3-特别监管")
    private String superviseType;

    @ApiModelProperty("企业数量")
    private String num;

    @ApiModelProperty("企业数量")
    private BigDecimal rate;
}
