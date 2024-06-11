package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 第三方单位审核园区端
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitAuditDTO", description = "第三方单位对象园区端")
public class TbThirdUnitAuditDTO implements Serializable {

    @ApiModelProperty("第三方单位ID")
    @NotEmpty(message = "第三方单位ID不能为空")
    private String thirdUnitId;

    @ApiModelProperty("审核结果：2-审核通过 3-审核驳回")
    @NotEmpty(message = "审核结果不能为空")
    private String status;

}
