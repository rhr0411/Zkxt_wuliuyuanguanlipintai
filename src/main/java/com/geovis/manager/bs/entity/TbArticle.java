package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章管理
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_article")
@ApiModel(value = "TbArticle对象", description = "文章管理")
public class TbArticle extends BaseEntity {

    @ApiModelProperty("文章名称")
    private String articleName;

    @ApiModelProperty("文章类型：1-宣传栏 0-考试培训")
    private String articleType;

    @ApiModelProperty("工种类型：宣传栏是所有人，开始培训对应第三方单位")
    private String workType;

    @ApiModelProperty("文章附件ID")
    private String articleFileId;

    @ApiModelProperty("首页图片ID")
    private String imageId;

    @ApiModelProperty("上传时间")
    private LocalDateTime uploadTime;


}
