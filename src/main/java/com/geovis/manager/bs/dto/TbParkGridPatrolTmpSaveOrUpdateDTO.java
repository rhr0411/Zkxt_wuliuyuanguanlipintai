package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 园区网格_巡检设置保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolTmpSaveOrUpdateDTO", description = "园区网格_巡检设置保存或更新")
public class TbParkGridPatrolTmpSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("队伍名称")
    @NotEmpty(message = "队伍名称不能为空")
    private String teamName;

    @ApiModelProperty("负责片区ID")
    @NotEmpty(message = "负责片区ID不能为空")
    private String gridId;

    @ApiModelProperty("队长联系电话")
    private String teamLeaderPhone;

    @ApiModelProperty("巡查任务")
    @NotEmpty(message = "巡查任务不能为空")
    private String patrolTask;

    @ApiModelProperty("巡查路线")
    @NotEmpty(message = "巡查路线不能为空")
    private String patrolRoute;

    @ApiModelProperty("巡查频率，如：10:00-12:00")
    @NotNull(message = "巡查频率不能为空")
    private List<String> patrolFrequencyList;

    @ApiModelProperty("队长Id")
    @NotEmpty(message = "队长Id不能为空")
    private String teamLeaderId;

    @ApiModelProperty("成员列表ID")
    private List<String> userIdList;


}
