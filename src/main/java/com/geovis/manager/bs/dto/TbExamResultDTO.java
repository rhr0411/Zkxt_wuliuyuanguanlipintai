package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbExamResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 成绩管理和证书发放
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamResultDTO", description = "成绩管理和证书发放")
public class TbExamResultDTO extends TbExamResult {

    @ApiModelProperty("用户真实名称")
    private String userRealName;

    @ApiModelProperty("第三方单位名称")
    private String thirdUnitName;

    @ApiModelProperty(value = "工种")
    private String workerType;

    @ApiModelProperty("学习进度")
    private BigDecimal studyRate;

    @ApiModelProperty("证书状态")
    private String certificateStatus;


}
