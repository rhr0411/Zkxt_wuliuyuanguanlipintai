package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 行政区划字典表
 * </p>
 *
 * @author 曾德实
 * @since 2023-07-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("system_region")
@ApiModel(value = "SystemRegion对象", description = "行政区划字典表")
public class SystemRegion implements Serializable {

    @ApiModelProperty("主键id")
    @TableId
    private String id;

    @ApiModelProperty("上级ID，一级为0")
    private String parentId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("层级")
    private Integer treeLevel;

    @ApiModelProperty("是否叶子节点  0：否   1：是")
    private Integer leaf;

    @ApiModelProperty("排序")
    private Long sort;

}
