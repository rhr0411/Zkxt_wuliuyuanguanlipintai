package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 邮件提醒
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitEmailRemindDTO", description = "邮件提醒")
public class TbThirdUnitEmailRemindDTO implements Serializable {

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("内容")
    private String content;

}
