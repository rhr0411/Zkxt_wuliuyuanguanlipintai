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
 * 第三方单位和企业关联表
 * </p>
 *
 * @author zengds
 * @since 2024-04-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_third_unit_enterprise")
@ApiModel(value = "TbThirdUnitEnterprise对象", description = "第三方单位和企业关联表")
public class TbThirdUnitEnterprise extends BaseEntity {

    @ApiModelProperty("第三方单位ID")
    private String thirdUnitId;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("培训状态：1-已培训 0-未培训")
    private String trainStatus;

    @ApiModelProperty("培训时间")
    private LocalDate trainTime;

    @ApiModelProperty("培训内容")
    private String trainContent;


}
