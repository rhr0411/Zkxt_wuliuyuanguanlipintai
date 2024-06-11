package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 企业添加或更新
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseSaveOrUpdateDTO", description = "企业添加或更新")
public class TbEnterpriseSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("企业名称")
    private String name;

    @ApiModelProperty("所属行业：1-生产制造业 2-储存与批发零售 3-运输业 4-使用与处置")
    private String industry;

    @ApiModelProperty("主要负责人名称")
    private String mainMasterName;

    @ApiModelProperty("主要负责人电话")
    private String mainMasterPhone;

    @ApiModelProperty("安全负责人名称")
    private String safeMasterName;

    @ApiModelProperty("安全负责人电话")
    private String safeMasterPhone;

    @ApiModelProperty("安全生产许可编号")
    private String safeProductAuthno;

    @ApiModelProperty("企业详情地址")
    private String address;

    @ApiModelProperty("统一社会信用代码")
    private String socialCode;

    @ApiModelProperty("生产场所地址")
    private String productAddress;

    @ApiModelProperty("特殊作业人员人数")
    private Integer specialPersonNum;

    @ApiModelProperty("有效期限-开始时间")
    private LocalDate expireDateStart;

    @ApiModelProperty("有效期限-结束时间")
    private LocalDate expireDateEnd;

    @ApiModelProperty("成立日期")
    private LocalDate establishmentDate;

    @ApiModelProperty("经济类型：1-私营经济 2-国有经济 3-股份制")
    private String economicType;

    @ApiModelProperty("行政区划编码")
    private String regionCode;

    @ApiModelProperty("职工人数")
    private Integer workerNum;

    @ApiModelProperty("从业人员人数")
    private Integer employeeNum;

    @ApiModelProperty("剧毒化学品作业人员人数")
    private Integer highlyToxicNum;

    @ApiModelProperty("法人代表")
    private String legalPerson;

    @ApiModelProperty("危险化学品作业人员人数")
    private Integer dangerChemicalsNum;

    @ApiModelProperty("营业执照经营范围")
    private String natureOfBusiness;

    @ApiModelProperty("行政区划名称")
    private String regionName;

    @ApiModelProperty("诚信等级")
    private String integrityLevel;

    @ApiModelProperty("监管分类：1-一般监督 2-终点关注 3-特别监管")
    private String superviseType;

    @ApiModelProperty("网格ID")
    private String gridId;

    @ApiModelProperty("诚信等级备注")
    private String integrityLevelRemark;

    @ApiModelProperty("基本信息完整度")
    private BigDecimal completeness;

    @ApiModelProperty("附件列表")
    private List<String> fileIdList;

}
