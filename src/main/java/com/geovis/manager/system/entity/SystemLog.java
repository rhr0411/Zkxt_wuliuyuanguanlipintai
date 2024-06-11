package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author 王响
 * @since 2021-01-25
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_log")
@ApiModel(value = "SystemLog对象", description = "日志表")
public class SystemLog extends BaseEntity {

    @ApiModelProperty(value = "操作方式:POST/GET")
    @Length(max = 12, message = "操作方式:POST/GET最长不能超过{12}个字符")
    private String requestType;

    @ApiModelProperty(value = "请求URI")
    @Length(max = 255, message = "请求URI最长不能超过{255}个字符")
    private String requestUri;

    @ApiModelProperty(value = "访问入参")
    private String requestParams;

    @ApiModelProperty(value = "访问出参")
    private String requestReturn;

    @ApiModelProperty(value = "异常类名")
    @Length(max = 255, message = "异常类名最长不能超过{255}个字符")
    private String exceptionClass;

    @ApiModelProperty(value = "异常信息")
    private String exceptionMessage;

    @ApiModelProperty(value = "执行开始时间")
    private LocalDateTime beginRequestTime;

    @ApiModelProperty(value = "执行结束时间")
    private LocalDateTime endRequestTime;

    @ApiModelProperty(value = "执行耗时")
    private Long spendTime;

    @ApiModelProperty(value = "日志说明")
    private String logDescription;

    @ApiModelProperty(value = "日志级别：INFO/ERROR")
    private String logLevel;

    @ApiModelProperty(value = "路由编码")
    private String routeCode;

    @ApiModelProperty(value = "服务ID")
    private String resourceId;

    @ApiModelProperty(value = "服务名称")
    private String resourceName;

    @ApiModelProperty(value = "服务ID")
    private String groupId;

    @ApiModelProperty(value = "服务名称")
    private String groupName;

    @ApiModelProperty("当前调用ID")
    private String threadId;

    @ApiModelProperty("调用者ID")
    private String applyId;


}
