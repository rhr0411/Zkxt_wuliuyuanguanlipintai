package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患整改移动端上报
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsHandleAppSaveDTO", description = "风险隐患管理_隐患整改移动端上报")
public class TbRiskHazardsAppSaveDTO implements Serializable {

    @ApiModelProperty("上报人名称")
    @NotEmpty(message = "上报人名称不能为空")
    private String reporterName;

    @ApiModelProperty("上报人电话")
    @NotEmpty(message = "上报人电话不能为空")
    private String reporterPhoneNo;

    @ApiModelProperty("隐患描述")
    @NotEmpty(message = "隐患描述不能为空")
    private String description;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("经度")
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @ApiModelProperty("纬度")
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @ApiModelProperty("隐患图片ID列表")
    private List<String> riskHazardsIdList;


}
