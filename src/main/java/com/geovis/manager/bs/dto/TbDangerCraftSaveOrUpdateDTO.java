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
 * 危险化工工艺保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbDangerCraftSaveOrUpdateDTO", description = "危险化工工艺保存或更新")
public class TbDangerCraftSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("工艺名称")
    @NotEmpty(message = "工艺名称不能为空")
    private String name;

    @ApiModelProperty("典型工艺")
    private String typicalProcesses;

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
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;


}
