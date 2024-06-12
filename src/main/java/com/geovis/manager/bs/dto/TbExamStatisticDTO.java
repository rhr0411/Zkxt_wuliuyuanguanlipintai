package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class TbExamStatisticDTO implements Serializable {


    @ApiModelProperty("考试平均得分")
    private String examScore;

    @ApiModelProperty("考试完成率")
    private String status;

    @ApiModelProperty("考试合格率")
    private String result;

    @ApiModelProperty("证书总数")
    private String certificates;

    @ApiModelProperty("过期证书总数")
    private String expireCertificates;
}
