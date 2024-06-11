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
 * 安全承诺书
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_commitment")
@ApiModel(value = "TbCommitment对象", description = "安全承诺书")
public class TbCommitment extends BaseEntity {

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


}
