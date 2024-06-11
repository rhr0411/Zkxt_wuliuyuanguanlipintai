package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患整改
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsHandleDTO", description = "风险隐患管理_隐患整改")
public class TbRiskHazardsHandleDTO implements Serializable {

    @ApiModelProperty("主键ID")
    @NotEmpty(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty("负责人名称")
    private String masterName;

    @ApiModelProperty("负责人联系电话")
    private String masterPhone;

    @ApiModelProperty("投入资金")
    private BigDecimal spendAmount;

    @ApiModelProperty("整改措施")
    private String handleMethod;

    @ApiModelProperty("整改完成日期")
    private LocalDate handleRealDate;

    @ApiModelProperty("隐患整改附件ID列表")
    private List<String> handleFileIdList;

    @ApiModelProperty("隐患整改图片ID列表")
    private List<String> handleImageIdList;

}
