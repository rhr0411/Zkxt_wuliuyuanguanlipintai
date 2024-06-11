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
 * 文章管理保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbArticleSaveOrUpdateDTO", description = "文章管理")
public class TbArticleSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("文章名称")
    @NotEmpty(message = "文章名称不能为空")
    private String articleName;

    @ApiModelProperty("文章类型：1-宣传栏 0-考试培训")
    @NotEmpty(message = "文章类型不能为空")
    private String articleType;

    @ApiModelProperty("工种类型：宣传栏是所有人，开始培训对应第三方单位")
    private String workType;

    @ApiModelProperty("文章附件ID")
    private String articleFileId;

    @ApiModelProperty("首页图片ID")
    private String imageId;

}
