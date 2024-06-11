package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 检查计划保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCheckPlanSaveOrUpdateDTO", description = "检查计划保存或更新")
public class TbCheckPlanSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("检查计划名称")
    @NotEmpty(message = "检查计划名称不能为空")
    private String name;

    @ApiModelProperty("检查类型：1-日常检查 2-专项检查 3-重要节假日活动 4-其他")
    @NotEmpty(message = "检查类型不能为空")
    private String type;

    @ApiModelProperty("检查周期_开始时间")
    @NotEmpty(message = "检查周期_开始时间不能为空")
    private LocalDate checkStartDate;

    @ApiModelProperty("检查周期_结束时间")
    @NotEmpty(message = "检查周期_结束时间不能为空")
    private LocalDate checkEndDate;

    @ApiModelProperty("检查部门")
    private String deptName;

    @ApiModelProperty("检查人员")
    private String checker;

    @ApiModelProperty("检查方案文件ID")
    private String checkPlanFileId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("企业Id列表")
    private List<String> enterpriseIdList;

}
