package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 设备信息查询
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpQueryDTO", description = "设备信息查询")
public class TbEqpQueryDTO implements Serializable {

    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("所属类型")
    private String type;

    @ApiModelProperty("设备编号")
    private String eqpCode;

    @ApiModelProperty("设备规格")
    private String eqpSpecifications;

    @ApiModelProperty("数量")
    private Integer eqpNum;

    @ApiModelProperty("使用日期")
    private LocalDate useDate;

    @ApiModelProperty("检验日期开始时间")
    private LocalDate checkDateStart;

    @ApiModelProperty("检验日期开始时间")
    private LocalDate checkDateEnd;

    @ApiModelProperty("设备状态：1-正常 2-停用 3-检修")
    private String status;


}
