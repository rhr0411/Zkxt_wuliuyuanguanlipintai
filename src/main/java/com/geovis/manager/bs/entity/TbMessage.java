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
 * 消息提醒
 * </p>
 *
 * @author zengds
 * @since 2024-05-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_message")
@ApiModel(value = "TbMessage对象", description = "消息提醒")
public class TbMessage extends BaseEntity {

    @ApiModelProperty("消息类型：1-隐患预警 2-开停车冲突 3-年度履诺报告到期 4-其他")
    private String type;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("消息时间")
    private LocalDateTime dataTime;


}
