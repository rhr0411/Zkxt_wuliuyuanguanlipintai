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
 * 重大危险源
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_danger_source")
@ApiModel(value = "TbDangerSource对象", description = "重大危险源")
public class TbDangerSource extends BaseEntity {

    @ApiModelProperty("危化品名称")
    private String name;

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    private String source;

    @ApiModelProperty("危险源等级：1-一级 2-二级 3-三级 4-四级 0-非重大")
    private String level;

    @ApiModelProperty("责任人")
    private String responsiblePerson;

    @ApiModelProperty("联系电话")
    private String connectPhone;

    @ApiModelProperty("预案名称")
    private String planName;

    @ApiModelProperty("预案文件ID")
    private String planFileId;

    @ApiModelProperty("安全控制措施")
    private String controlSafeMeasure;

    @ApiModelProperty("位置描述")
    private String addressDesc;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("危险物质")
    private String dangerThings;

    @ApiModelProperty("临界值")
    private String criticalValue;


}
