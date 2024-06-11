package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 责任清单查询
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbResponsibilityQueryDTO", description = "责任清单查询")
public class TbResponsibilityQueryDTO implements Serializable {

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("上传时间-开始时间")
    private LocalDateTime uploadTimeStart;

    @ApiModelProperty("上传时间-结束时间")
    private LocalDateTime uploadTimeEnd;

}
