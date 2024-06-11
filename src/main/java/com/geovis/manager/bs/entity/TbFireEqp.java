package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@TableName("tb_fire_eqp")
@ApiModel(value = "TbFireEqp对象", description = "消防设施")
public class TbFireEqp extends BaseEntity {

    @ApiModelProperty("消防设备名称")
    private String name;

    @ApiModelProperty("设备规格")
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
    private String eqpValidity;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
