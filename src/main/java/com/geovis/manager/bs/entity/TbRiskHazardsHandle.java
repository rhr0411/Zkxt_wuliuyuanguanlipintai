package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@TableName("tb_risk_hazards_handle")
@ApiModel(value = "TbRiskHazardsHandle对象", description = "风险隐患管理_隐患整改")
public class TbRiskHazardsHandle extends BaseEntity {

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("核查人员")
    private String checkPerson;

    @ApiModelProperty("复查意见")
    private String checkView;

    @ApiModelProperty("隐患来源:1-日常检查 2-专项检查 3-重要节假日活动 4-其他 5-随手拍")
    private String dataSource;

    @ApiModelProperty("上报人名称")
    private String reporterName;

    @ApiModelProperty("上报人电话")
    private String reporterPhoneNo;

    @ApiModelProperty("隐患级别：1-一般隐患 2-重大隐患")
    private String level;

    @ApiModelProperty("隐患描述")
    private String description;

    @ApiModelProperty("上报时间")
    private LocalDateTime reportTime;

    @ApiModelProperty("整改限制开始日期")
    private LocalDate handlePlanStartDate;

    @ApiModelProperty("整改限制结束日期")
    private LocalDate handlePlanEndDate;

    @ApiModelProperty("复查日期")
    private LocalDate checkDate;

    @ApiModelProperty("复查部门")
    private String checkDept;

    @ApiModelProperty("整改完成日期")
    private LocalDate handleRealDate;

    @ApiModelProperty("上报处理状态：0-待处理 1-已处理")
    private String handleStatus;

    @ApiModelProperty("督办整改状态：1-待复查 2-待整改 3-逾期未整改 4-已完成")
    private String supervisionStatus;

    @ApiModelProperty("负责人名称")
    private String masterName;

    @ApiModelProperty("负责人联系电话")
    private String masterPhone;

    @ApiModelProperty("投入资金")
    private BigDecimal spendAmount;

    @ApiModelProperty("整改措施")
    private String handleMethod;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
