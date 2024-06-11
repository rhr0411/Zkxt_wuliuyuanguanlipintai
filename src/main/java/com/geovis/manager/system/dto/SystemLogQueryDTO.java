package com.geovis.manager.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志查询
 * </p>
 *
 * @author 王响
 * @since 2022-07-28
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemLogQueryDTO", description = "日志查询DTO")
public class SystemLogQueryDTO implements Serializable {

    @ApiModelProperty(value = "执行耗时")
    private Long spendTime;

    @ApiModelProperty(value = "操作方式:POST/GET")
    @Length(max = 12, message = "操作方式:POST/GET最长不能超过{12}个字符")
    private String requestType;

    @ApiModelProperty(value = "请求URI")
    @Length(max = 255, message = "请求URI最长不能超过{255}个字符")
    private String requestUri;

    @ApiModelProperty(value = "方法类")
    @Length(max = 128, message = "方法类最长不能超过{128}个字符")
    private String className;

    @ApiModelProperty(value = "方法名")
    @Length(max = 128, message = "方法名最长不能超过{128}个字符")
    private String methodName;

    @ApiModelProperty(value = "访问入参")
    private String requestParams;

    @ApiModelProperty(value = "访问出参")
    private String requestReturn;

    @ApiModelProperty(value = "异常类名")
    @Length(max = 255, message = "异常类名最长不能超过{255}个字符")
    private String exceptionClass;

    @ApiModelProperty(value = "异常信息")
    private String exceptionMessage;

    @ApiModelProperty(value = "执行开始时间-开始")
    private LocalDateTime beginRequestTimeStart;

    @ApiModelProperty(value = "执行开始时间-结束")
    private LocalDateTime beginRequestTimeEnd;

    @ApiModelProperty(value = "日志说明")
    private String logDescription;

    @ApiModelProperty(value = "日志级别：INFO/ERROR")
    private String logLevel;

    @ApiModelProperty(value = "操作人")
    private String createBy;

}
