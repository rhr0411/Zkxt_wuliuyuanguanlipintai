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
 * 机构表
 * </p>
 *
 * @author 王响
 * @since 2022-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_dept")
@ApiModel(value = "SystemDept对象", description = "机构表")
public class SystemDept extends BaseEntity {

    @ApiModelProperty(value = "父主键")
    private String parentId;

    @ApiModelProperty(value = "机构名")
    private String deptName;

    @ApiModelProperty(value = "机构全称")
    private String fullName;

    @ApiModelProperty(value = "排序")
    private Integer idx;

    @ApiModelProperty(value = "备注")
    private String remark;

}
