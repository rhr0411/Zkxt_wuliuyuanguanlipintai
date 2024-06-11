package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 企业信息查询
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseQueryDTO", description = "企业信息查询")
public class TbEnterpriseQueryDTO implements Serializable {

    @ApiModelProperty("所属行业：1-生产制造业 2-储存与批发零售 3-运输业 4-使用与处置")
    private String industry;

    @ApiModelProperty("有效期限-开始时间")
    private LocalDate expireDateStart;

    @ApiModelProperty("有效期限-结束时间")
    private LocalDate expireDateEnd;

    @ApiModelProperty("创建时间-开始时间")
    private LocalDateTime createTimeStart;

    @ApiModelProperty("创建时间-结束时间")
    private LocalDateTime createTimeEnd;

    @ApiModelProperty("经济类型：1-私营经济 2-国有经济 3-股份制")
    private String economicType;

    @ApiModelProperty("诚信等级")
    private String integrityLevel;

    @ApiModelProperty("监管分类：1-一般监督 2-终点关注 3-特别监管")
    private String superviseType;

    @ApiModelProperty("是否进入黑名单:1-是 0-否")
    private String inBlacklist;

    @ApiModelProperty("企业名称")
    private String name;
}
