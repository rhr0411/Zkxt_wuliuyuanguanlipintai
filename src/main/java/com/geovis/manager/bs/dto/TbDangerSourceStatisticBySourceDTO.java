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
@ApiModel(value = "TbDangerSourceStatisticDTO", description = "重大危险源数据展示")
public class TbDangerSourceStatisticBySourceDTO implements Serializable {

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source1;

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source2;

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source3;


    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source4;

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source5;

    @ApiModelProperty("重大危险源：1-储罐区 2-库区 3-生产场所 4-压力管道 5-锅炉 6-压力容器")
    @NotEmpty(message = "重大危险源不能为空")
    private String source6;


}
