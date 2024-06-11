package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 第三方单位安全教育记录查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitSafeEduRecordQueryDTO", description = "第三方单位安全教育记录查询")
public class TbThirdUnitSafeEduRecordQueryDTO implements Serializable {

    @ApiModelProperty("第三方单位ID")
    @NotEmpty(message = "第三方单位ID不能为空")
    private String thirdUnitId;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

}
