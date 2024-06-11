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
 * 信用信息报送
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_credit_info_report")
@ApiModel(value = "TbCreditInfoReport对象", description = "信用信息报送")
public class TbCreditInfoReport extends BaseEntity {

    @ApiModelProperty("信用信息类型：1-行政许可 0-行政处罚")
    private String type;

    @ApiModelProperty("信用信息详情")
    private String description;

    @ApiModelProperty("关联企业")
    private String enterpriseId;

    @ApiModelProperty("报送责任人")
    private String reportName;

    @ApiModelProperty("报送时间")
    private LocalDateTime reportTime;

    @ApiModelProperty("报送状态：1-已报送 0-未报送")
    private String reportStatus;


}
