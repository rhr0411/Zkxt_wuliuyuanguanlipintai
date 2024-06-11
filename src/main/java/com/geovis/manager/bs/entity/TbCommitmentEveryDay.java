package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 安全承诺企业现状
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_commitment_every_day")
@ApiModel(value = "TbCommitmentEveryDay对象", description = "安全承诺企业现状")
public class TbCommitmentEveryDay extends BaseEntity {

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

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
