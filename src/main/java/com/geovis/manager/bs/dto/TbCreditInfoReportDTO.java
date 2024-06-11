package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbCreditInfoReport;
import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 信用信息报送对象
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCreditInfoReportDTO", description = "信用信息报送对象")
public class TbCreditInfoReportDTO extends TbCreditInfoReport {

    @ApiModelProperty("附件列表")
    private List<SystemFileDTO> fileList;

    @ApiModelProperty("报送附件列表")
    private List<SystemFileDTO> reportFileList;

}
