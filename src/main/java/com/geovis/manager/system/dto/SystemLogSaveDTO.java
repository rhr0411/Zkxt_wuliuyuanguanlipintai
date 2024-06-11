package com.geovis.manager.system.dto;


import com.geovis.manager.system.entity.SystemLog;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志保存DTO
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
public class SystemLogSaveDTO extends SystemLog {

}