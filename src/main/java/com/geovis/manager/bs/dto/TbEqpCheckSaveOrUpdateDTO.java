package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 装置开停车和大检修管理保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpCheckSaveOrUpdateDTO", description = "装置开停车和大检修管理保存或更新")
public class TbEqpCheckSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("类别:1-装置开停车 2-大检修")
    @NotEmpty(message = "类别不能为空")
    private String type;

    @ApiModelProperty("检修开始时间")
    @NotNull(message = "检修开始时间不能为空")
    private LocalDateTime checkTimeStart;

    @ApiModelProperty("检修结束时间")
    @NotNull(message = "检修结束时间不能为空")
    private LocalDateTime checkTimeEnd;

    @ApiModelProperty("检修地址")
    @NotEmpty(message = "检修地址不能为空")
    private String checkAddress;

    @ApiModelProperty("检修方案")
    @NotEmpty(message = "检修方案不能为空")
    private String checkPlan;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
