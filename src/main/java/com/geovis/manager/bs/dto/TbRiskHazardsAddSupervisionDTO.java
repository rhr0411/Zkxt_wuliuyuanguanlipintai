package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患新增并督办
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsAddSupervisionDTO", description = "风险隐患管理_隐患新增并督办")
public class TbRiskHazardsAddSupervisionDTO implements Serializable {

    @ApiModelProperty("隐患来源:1-日常检查 2-专项检查 3-重要节假日活动 4-其他 5-随手拍")
    @NotEmpty(message = "隐患来源不能为空")
    private String dataSource;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("隐患级别：1-一般隐患 2-重大隐患")
    @NotEmpty(message = "隐患级别不能为空")
    private String level;

    @ApiModelProperty("整改限制开始日期")
    @NotNull(message = "整改限制开始日期不能为空")
    private LocalDate handlePlanStartDate;

    @ApiModelProperty("整改限制结束日期")
    @NotNull(message = "整改限制结束日期不能为空")
    private LocalDate handlePlanEndDate;

    @ApiModelProperty("隐患描述")
    @NotEmpty(message = "隐患描述不能为空")
    private String description;

    @ApiModelProperty("经度")
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @ApiModelProperty("纬度")
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @ApiModelProperty("隐患图片ID列表")
    private List<String> riskHazardsIdList;

}
