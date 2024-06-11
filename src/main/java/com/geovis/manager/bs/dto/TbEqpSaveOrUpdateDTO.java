package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 企业设备更新或保存
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpSaveOrUpdateDTO", description = "企业设备更新或保存")
public class TbEqpSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("设备名称")
    private String name;

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

    @ApiModelProperty("检验日期")
    private LocalDate checkDate;

    @ApiModelProperty("设备状态：1-正常 2-停用 3-检修")
    private String status;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

}
