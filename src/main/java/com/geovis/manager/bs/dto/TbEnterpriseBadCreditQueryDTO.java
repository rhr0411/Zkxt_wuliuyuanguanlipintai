package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业不良信用管理查询对象
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseBadCreditQueryDTO", description = "企业不良信用管理查询对象")
public class TbEnterpriseBadCreditQueryDTO implements Serializable {

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("创建时间-开始时间")
    private LocalDateTime startCreateTime;

    @ApiModelProperty("创建时间-结束时间")
    private LocalDateTime endCreateTime;

}
