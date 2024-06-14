package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridDangerNumDto", description = "园区网格_危险源数量")
public class TbParkGridDangerNumDto {

    @ApiModelProperty("网格名称")
    private String name;

    @ApiModelProperty("危险数量/风险数量")
    private String num;
}
