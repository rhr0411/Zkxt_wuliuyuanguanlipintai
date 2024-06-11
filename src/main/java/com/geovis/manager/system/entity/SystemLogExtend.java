package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 日志扩展表
 * </p>
 *
 * @author 曾德实
 * @since 2022-10-10
 */
@Setter
@Getter
@Accessors(chain = true)
@TableName("system_log_extend")
@ApiModel(value = "SystemLogExtend", description = "日志扩展表")
public class SystemLogExtend implements Serializable {

    @ApiModelProperty("主键id")
    @TableId
    private String id;

    @ApiModelProperty(value = "日志ID")
    private String logId;

    @ApiModelProperty(value = "访问出参")
    private String logReturn;

    @ApiModelProperty(value = "异常信息")
    private String logException;

}
