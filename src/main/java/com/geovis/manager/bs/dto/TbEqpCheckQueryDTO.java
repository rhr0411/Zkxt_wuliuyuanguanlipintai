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
 * 装置开停车和大检修管理查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEqpCheckQueryDTO", description = "装置开停车和大检修管理查询")
public class TbEqpCheckQueryDTO implements Serializable {

    @ApiModelProperty("类别:1-装置开停车 2-大检修")
    private String type;

    @ApiModelProperty("检修开始时间")
    private LocalDateTime checkTimeStart;

    @ApiModelProperty("检修结束时间")
    private LocalDateTime checkTimeEnd;

    @ApiModelProperty("检修地址")
    private String checkAddress;

    @ApiModelProperty("状态：1-正常 0-冲突预警")
    private String status;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
