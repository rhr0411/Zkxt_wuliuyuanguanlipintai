package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbExamRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 交卷结果
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamEndResultDTO", description = "交卷结果")
public class TbExamEndResultDTO extends TbExamRecord {

    @ApiModelProperty("考试结果：1-合格 0-不合格")
    private String result;

    @ApiModelProperty("证书合格有效期开始日期")
    private LocalDate certificateStartDate;

    @ApiModelProperty("证书合格有效期结束日期")
    private LocalDate certificateExpireDate;

}
