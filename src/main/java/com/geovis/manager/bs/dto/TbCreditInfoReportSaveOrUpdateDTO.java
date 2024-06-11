package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 信用信息报送保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCreditInfoReportSaveOrUpdateDTO", description = "信用信息报送保存或更新")
public class TbCreditInfoReportSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("信用信息类型：1-行政许可 0-行政处罚")
    @NotEmpty(message = "不能为空")
    private String type;

    @ApiModelProperty("信用信息详情")
    private String description;

    @ApiModelProperty("关联企业")
    private String enterpriseId;

    @ApiModelProperty("报送状态：1-已报送 0-未报送")
    private String reportStatus;

    @ApiModelProperty("附件ID列表")
    private List<String> fileIdList;


}
