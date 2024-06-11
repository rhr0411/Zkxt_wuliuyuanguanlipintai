package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 安全与环境管理信息表添加或更新
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbSafeEnviroManagerSaveOrUpdateDTO", description = "安全与环境管理信息表添加或更新")
public class TbSafeEnviroManagerSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("文件名称")
    @NotEmpty(message = "文件名称不能为空")
    private String fileName;

    @ApiModelProperty("所属类型")
    @NotEmpty(message = "所属类型不能为空")
    private String type;

    @ApiModelProperty("文件ID")
    @NotEmpty(message = "文件ID不能为空")
    private String fileId;

    @ApiModelProperty("企业ID")
    @NotEmpty(message = "企业ID不能为空")
    private String enterpriseId;

}
