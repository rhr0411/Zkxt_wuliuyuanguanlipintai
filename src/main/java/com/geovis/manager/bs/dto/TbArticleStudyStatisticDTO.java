package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 文章管理学习统计
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbArticleStudyStatisticDTO", description = "文章管理学习统计")
public class TbArticleStudyStatisticDTO implements Serializable {

    @ApiModelProperty("是否达标：1-已达标 0-未达标")
    private String hasQualify;

    @ApiModelProperty("完成学习率")
    private BigDecimal completeRate;


}
