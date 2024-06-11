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
 * 危险化工工艺
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_danger_craft")
@ApiModel(value = "TbDangerCraft对象", description = "危险化工工艺")
public class TbDangerCraft extends BaseEntity {

    // 自定义:1-光气及光气化工艺 2-电解工艺（氯碱）3-氯化工艺 4-硝化工艺 5-合成氨工艺 6-裂解（裂化）工艺 7-氟化工艺 8-
    @ApiModelProperty("工艺名称")
    private String name;

    @ApiModelProperty("典型工艺")
    private String typicalProcesses;

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
