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
 * 危险化学品
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_danger_chemical")
@ApiModel(value = "TbDangerChemical对象", description = "危险化学品")
public class TbDangerChemical extends BaseEntity {

    @ApiModelProperty("危化品名称")
    private String name;

    @ApiModelProperty("危化品种类")
    private String type;

    @ApiModelProperty("重点监控参数")
    private String monitorParams;

    @ApiModelProperty("责任人")
    private String responsiblePerson;

    @ApiModelProperty("联系电话")
    private String connectPhone;

    @ApiModelProperty("预案名称")
    private String planName;

    @ApiModelProperty("预案文件ID")
    private String planFileId;

    @ApiModelProperty("安全控制要求")
    private String controlSafeRequire;

    @ApiModelProperty("控制方案")
    private String controlPlan;

    @ApiModelProperty("企业ID")
    private String enterpriseId;


}
