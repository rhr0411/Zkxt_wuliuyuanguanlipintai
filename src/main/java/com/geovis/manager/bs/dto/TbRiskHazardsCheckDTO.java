package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患复查
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsCheckDTO", description = "风险隐患管理_隐患复查")
public class TbRiskHazardsCheckDTO implements Serializable {

    @ApiModelProperty("主键ID")
    @NotEmpty(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty("复查日期")
    private LocalDate checkDate;

    @ApiModelProperty("复查部门")
    private String checkDept;

    @ApiModelProperty("核查人员")
    private String checkPerson;

    @ApiModelProperty("复查意见")
    private String checkView;

    @ApiModelProperty("复查结果状态：1-复查通过 0-复查驳回")
    private String checkStatus;

    @ApiModelProperty("隐患复查图片ID列表")
    private List<String> checkImageIdList;

    @ApiModelProperty("隐患复查附件ID列表")
    private List<String> checkFileIdList;

}
