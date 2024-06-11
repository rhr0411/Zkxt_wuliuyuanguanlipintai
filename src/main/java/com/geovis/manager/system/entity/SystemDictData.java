package com.geovis.manager.system.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_dict_data")
@ApiModel(value = "SystemDictData对象", description = "字典数据表")
public class SystemDictData extends BaseEntity {

    @ApiModelProperty(value = "字典id")
    private String dictId;

    @ApiModelProperty(value = "字典数据key")
    private String dataKey;

    @ApiModelProperty(value = "字典数据value")
    private String dataValue;

    @ApiModelProperty(value = "上级字典数据key（构建树状使用）")
    private String dataParentKey;

    @ApiModelProperty(value = "排序")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "数据状态（1-启用，0-禁用）")
    private String dataStatus;

}
