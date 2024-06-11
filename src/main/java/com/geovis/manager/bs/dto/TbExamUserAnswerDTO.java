package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户试题答案
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamUserAnswerDTO", description = "用户试题答案")
public class TbExamUserAnswerDTO implements Serializable {

    @ApiModelProperty("试题ID")
    @NotEmpty(message = "试题ID不能为空")
    private String examQuestionId;

    @ApiModelProperty("试题答案：格式-》单选：A，多选：A,B,C 判断：1-true 0-false")
    private String examAnswer;

}
