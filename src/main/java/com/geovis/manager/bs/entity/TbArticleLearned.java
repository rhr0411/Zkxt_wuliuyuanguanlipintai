package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章管理_安全教育_已学习人员
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_article_learned")
@ApiModel(value = "TbArticleLearned对象", description = "文章管理_安全教育_已学习人员")
public class TbArticleLearned extends BaseEntity {

    @ApiModelProperty("文章ID")
    private String articleId;

    @ApiModelProperty("用户ID")
    private String userId;


}
