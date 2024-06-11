package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 检查计划
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_check_plan")
@ApiModel(value = "TbCheckPlan对象", description = "检查计划")
public class TbCheckPlan extends BaseEntity {

    @ApiModelProperty("检查计划名称")
    private String name;

    @ApiModelProperty("检查类型：1-日常检查 2-专项检查 3-重要节假日活动 4-其他")
    private String type;

    @ApiModelProperty("检查周期_开始时间")
    private LocalDate checkStartDate;

    @ApiModelProperty("检查周期_结束时间")
    private LocalDate checkEndDate;

    @ApiModelProperty("检查部门")
    private String deptName;

    @ApiModelProperty("检查人员")
    private String checker;

    @ApiModelProperty("检查方案文件ID")
    private String checkPlanFileId;

    @ApiModelProperty("备注")
    private String remark;


}
