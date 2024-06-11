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
 * 字典表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_dict")
@ApiModel(value = "SystemDict对象", description = "字典表")
public class SystemDict extends BaseEntity {

    @ApiModelProperty(value = "字典码")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典类型(1-系统字典，2-业务字典)")
    private String type;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "数据状态（1-启用，0-禁用）")
    private String dataStatus;

}
