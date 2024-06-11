package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 信用信息报送
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCreditInfoReportReportDTO", description = "信用信息报送")
public class TbCreditInfoReportReportDTO extends TbCreditInfoReportSaveOrUpdateDTO {

    @ApiModelProperty("报送责任人")
    @NotEmpty(message = "报送责任人不能为空")
    private String reportName;

    @ApiModelProperty("报送时间")
    @NotNull(message = "报送时间人不能为空")
    private LocalDateTime reportTime;

    @ApiModelProperty("报送附件ID列表")
    private List<String> reportFileIdList;


}
