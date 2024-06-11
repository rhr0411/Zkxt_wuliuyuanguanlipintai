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
 * <p>
 * 消防设施
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbFireEqp对象", description = "消防设施")
public class TbFireEqpSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("消防设备名称")
    @NotEmpty(message = "消防设备名称不能为空")
    private String name;

    @ApiModelProperty("设备规格")
    @NotEmpty(message = "设备规格不能为空")
    private String specifications;

    @ApiModelProperty("设备数量")
    private Integer eqpNum;

    @ApiModelProperty("设备型号")
    private String eqpType;

    @ApiModelProperty("出厂日期")
    private LocalDate factoryDate;

    @ApiModelProperty("安装日期")
    private LocalDate installDate;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("设备位置")
    private String eqpPath;

    @ApiModelProperty("设备有效性：1-有效 0-无效")
    @NotEmpty(message = "设备有效性不能为空")
    private String eqpValidity;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;


}
