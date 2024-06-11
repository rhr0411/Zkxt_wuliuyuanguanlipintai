package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 成绩管理和证书发放查询
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamResultQueryDTO", description = "成绩管理和证书发放查询")
public class TbExamResultQueryDTO implements Serializable {

    @ApiModelProperty(value = "工种：1-特种作业 2-安全管理人员 3-危险化学品作业人员 4-剧毒化学品作业人员")
    private String workerType;

    @ApiModelProperty("证书状态:1-即将过期 2-已过期 3-已发放")
    private String certificateStatus;
}
