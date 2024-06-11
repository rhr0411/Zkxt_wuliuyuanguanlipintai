package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 业务模块-设备表
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_eqp")
@ApiModel(value = "TbEqp对象", description = "业务模块-设备表")
public class TbEqp extends BaseEntity {

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
