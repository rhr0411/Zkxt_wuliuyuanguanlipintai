package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事故事件管理
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_accident")
@ApiModel(value = "TbAccident对象", description = "事故事件管理")
public class TbAccident extends BaseEntity {

    @ApiModelProperty("事故类型:1-泄漏 2-爆炸 3-火灾 4-其他类型")
    private String type;

    @ApiModelProperty("发生时间")
    private LocalDateTime happenTime;

    @ApiModelProperty("事故地点")
    private String address;

    @ApiModelProperty("损失金额")
    private BigDecimal lossAmount;

    @ApiModelProperty("受伤人数")
    private Integer injuryNum;

    @ApiModelProperty("死亡人数")
    private Integer deadNum;

    @ApiModelProperty("事故描述")
    private String description;

    @ApiModelProperty("应急响应与救援情况")
    private String rescueDesc;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
