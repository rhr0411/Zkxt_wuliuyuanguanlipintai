package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 交卷
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamEndAnswerDTO", description = "交卷")
public class TbExamEndAnswerDTO implements Serializable {

    @ApiModelProperty("考试试卷ID")
    @NotEmpty(message = "考试试卷ID不能为空")
    private String examRecordId;

    @ApiModelProperty("答案列表")
    private List<TbExamUserAnswerDTO> userAnswerList;

}
