package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 重大危险源保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbDangerSourceSaveOrUpdateDTO", description = "重大危险源保存或更新")
public class TbDangerSourceSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("危化品名称")
    @NotEmpty(message = "危化品名称不能为空")
    private String name;

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source;

    @ApiModelProperty("危险源等级：1-一级 2-二级 3-三级 4-四级 0-非重大")
    @NotEmpty(message = "危险源等级不能为空")
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
