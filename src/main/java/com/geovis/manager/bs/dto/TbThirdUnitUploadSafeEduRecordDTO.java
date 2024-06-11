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
 * 第三方单位上传安全教育记录
 * </p>
 *
 * @author zengds
 * @since 2024-04-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbThirdUnitUploadSafeEduRecordDTO", description = "第三方单位上传安全教育记录")
public class TbThirdUnitUploadSafeEduRecordDTO implements Serializable {

    @ApiModelProperty("第三方单位ID")
    @NotEmpty(message = "第三方单位ID不能为空")
    private String thirdUnitId;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

    @ApiModelProperty("培训时间")
    @NotNull(message = "培训时间不能为空")
    private LocalDate trainTime;

    @ApiModelProperty("培训内容")
    @NotEmpty(message = "培训内容不能为空")
    private String trainContent;

    @ApiModelProperty("附件列表")
    private List<String> fileIdList;

    @ApiModelProperty("图片列表")
    private List<String> imageIdList;


}
