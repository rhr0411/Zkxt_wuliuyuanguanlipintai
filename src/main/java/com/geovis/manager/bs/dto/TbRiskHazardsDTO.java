package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbRiskHazardsHandle;
import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患详情
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbRiskHazardsDTO", description = "风险隐患管理_隐患详情")
public class TbRiskHazardsDTO extends TbRiskHazardsHandle {

    @ApiModelProperty("隐患上报图片列表")
    private List<SystemFileDTO> reportImageList;

    @ApiModelProperty("隐患整改图片列表")
    private List<SystemFileDTO> handleImageList;

    @ApiModelProperty("隐患整改附件列表")
    private List<SystemFileDTO> handleFileList;

    @ApiModelProperty("隐患复查附件列表")
    private List<SystemFileDTO> checkFileList;

    @ApiModelProperty("隐患复查图片列表")
    private List<SystemFileDTO> checkImageList;

}
