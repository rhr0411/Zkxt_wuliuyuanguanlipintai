package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbAccident;
import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 事故事件管理
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbAccidentDTO", description = "事故事件管理")
public class TbAccidentDTO extends TbAccident {

    @ApiModelProperty("附件列表")
    private List<SystemFileDTO> fileList;

    @ApiModelProperty("图片列表")
    private List<SystemFileDTO> imageList;

}
