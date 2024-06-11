package com.geovis.manager.bs.dto;

import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 第三方单位安全教育记录
 * </p>
 *
 * @author zengds
 * @since 2024-04-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitSafeEduRecordDTO", description = "第三方单位安全教育记录")
public class TbThirdUnitSafeEduRecordDTO implements Serializable {

    @ApiModelProperty("第三方单位ID")
    private String thirdUnitId;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("培训时间")
    private LocalDate trainTime;

    @ApiModelProperty("培训内容")
    private String trainContent;

    @ApiModelProperty("附件列表")
    private List<SystemFileDTO> fileList;

    @ApiModelProperty("图片列表")
    private List<SystemFileDTO> imageList;


}
