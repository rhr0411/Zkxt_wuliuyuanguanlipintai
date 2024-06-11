package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 责任清单管理
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_responsibility")
@ApiModel(value = "TbResponsibility对象", description = "责任清单管理")
public class TbResponsibility extends BaseEntity {

    @ApiModelProperty("文件ID")
    private String fileId;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("主要负责人")
    private String masterName;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("上传时间")
    @TableField(exist = false)
    private LocalDateTime uploadTime;


}
