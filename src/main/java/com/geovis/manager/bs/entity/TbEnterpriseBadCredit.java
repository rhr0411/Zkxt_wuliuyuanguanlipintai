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
 * 企业不良信用管理
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_enterprise_bad_credit")
@ApiModel(value = "TbEnterpriseBadCredit对象", description = "企业不良信用管理")
public class TbEnterpriseBadCredit extends BaseEntity {

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("不良信用描述")
    private String blackDesc;


}
