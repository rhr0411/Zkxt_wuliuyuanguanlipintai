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
 * 第三方单位
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_third_unit")
@ApiModel(value = "TbThirdUnit对象", description = "第三方单位")
public class TbThirdUnit extends BaseEntity {

    @ApiModelProperty("单位名称")
    private String name;

    @ApiModelProperty("主要负责人名称")
    private String masterName;

    @ApiModelProperty("主要负责人电话")
    private String masterPhone;

    @ApiModelProperty("资质等级")
    private String qualificationLevel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("单位地址")
    private String address;

    @ApiModelProperty("资质有效期")
    private LocalDate expireDate;

    @ApiModelProperty("单位类别")
    private String type;

    @ApiModelProperty("状态：1-待审核 2-审核通过 3-审核驳回")
    private String status;


}
