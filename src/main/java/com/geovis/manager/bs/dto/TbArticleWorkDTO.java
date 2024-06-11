package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章管理学习列表
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbArticleWorkDTO", description = "文章管理")
public class TbArticleWorkDTO extends TbArticle {

    @ApiModelProperty("是否已学习：1-已学习 0-微学习")
    private String learned;

}
