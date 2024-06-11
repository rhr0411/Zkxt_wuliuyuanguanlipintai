package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 安全承诺书
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbCommitmentEveryDaySaveDTO", description = "安全承诺书")
public class TbCommitmentEveryDaySaveDTO implements Serializable {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("承诺类别：1-每日安全承诺 2-年初承诺 3-年度履诺报告")
    private String type;

    @ApiModelProperty("承诺详情")
    private String commitDesc;

    @ApiModelProperty("安全风险等级:1-重大风险 2-较大风险 3-一般风险 0-低风险")
    private String level;

    @ApiModelProperty("承诺区间开始时间")
    private LocalDate commitStartDate;

    @ApiModelProperty("承诺区间结束时间")
    private LocalDate commitEndDate;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("安全承诺ID")
    private String commitId;

    @ApiModelProperty("特级动火作业")
    private Integer specialNum;

    @ApiModelProperty("一级动火作业")
    private Integer level1Num;

    @ApiModelProperty("二级动火作业")
    private Integer leve2Num;

    @ApiModelProperty("一级重大危险源")
    private Integer dangerSource1Num;

    @ApiModelProperty("二级重大危险源")
    private Integer dangerSource2Num;

    @ApiModelProperty("三级重大危险源")
    private Integer dangerSource3Num;

    @ApiModelProperty("四级重大危险源")
    private Integer dangerSource4Num;

    @ApiModelProperty("开车装置数")
    private Integer startEqpNum;

    @ApiModelProperty("停车装置数")
    private Integer stopEqpNum;

    @ApiModelProperty("检维修套数")
    private Integer checkEqpNum;

    private Integer checkEqpWorkNum;

    @ApiModelProperty("有无重大隐患：1-有 0-无")
    private String hasDanger;


}
