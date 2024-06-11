package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 责任清单管理保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbResponsibilitySaveOrUpdateDTO", description = "责任清单管理保存或更新")
public class TbResponsibilitySaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("文件ID")
    @NotEmpty(message = "文件ID不能为空")
    private String fileId;

    @ApiModelProperty("文件名称")
    @NotEmpty(message = "文件名称不能为空")
    private String fileName;

    @ApiModelProperty("主要负责人")
    @NotEmpty(message = "主要负责人不能为空")
    private String masterName;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

}
