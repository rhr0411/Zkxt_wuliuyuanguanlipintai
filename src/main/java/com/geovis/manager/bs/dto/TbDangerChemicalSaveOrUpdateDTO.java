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
 * 危险化学品保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbDangerChemicalSaveOrUpdateDTO", description = "危险化学品保存或更新")
public class TbDangerChemicalSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("危化品名称")
    @NotEmpty(message = "危化品名称不能为空")
    private String name;

    @ApiModelProperty("危化品种类")
    @NotEmpty(message = "危化品种类不能为空")
    private String type;

    @ApiModelProperty("重点监控参数")
    private String monitorParams;

    @ApiModelProperty("责任人")
    private String responsiblePerson;

    @ApiModelProperty("联系电话")
    private String connectPhone;

    @ApiModelProperty("预案名称")
    private String planName;

    @ApiModelProperty("预案文件ID")
    private String planFileId;

    @ApiModelProperty("安全控制要求")
    private String controlSafeRequire;

    @ApiModelProperty("控制方案")
    private String controlPlan;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
