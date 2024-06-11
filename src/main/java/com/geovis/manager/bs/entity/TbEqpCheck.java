package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 装置开停车和大检修管理
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_eqp_check")
@ApiModel(value = "TbEqpCheck对象", description = "装置开停车和大检修管理")
public class TbEqpCheck extends BaseEntity {

    @ApiModelProperty("类别:1-装置开停车 2-大检修")
    private String type;

    @ApiModelProperty("检修开始时间")
    private LocalDateTime checkTimeStart;

    @ApiModelProperty("检修结束时间")
    private LocalDateTime checkTimeEnd;

    @ApiModelProperty("检修地址")
    private String checkAddress;

    @ApiModelProperty("检修方案")
    private String checkPlan;

    @ApiModelProperty("状态：1-正常 0-冲突预警")
    private String status;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    private String enterpriseName;


}
