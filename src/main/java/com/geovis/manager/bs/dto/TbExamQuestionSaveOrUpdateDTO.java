package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 试题库保存或更新
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamQuestionSaveOrUpdateDTO", description = "试题库保存或更新")
public class TbExamQuestionSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("试题类型：1-单选 2-多选 3-判断")
    @NotEmpty(message = "试题类型不能为空")
    private String examType;

    @ApiModelProperty("试题内容")
    @NotEmpty(message = "试题内容不能为空")
    private String examContent;

    @ApiModelProperty("试题选项：格式如{\"A\":\"值A\",\"B\":\"值B\"}")
    private String examSelect;

    @ApiModelProperty("试题答案：格式-》单选：A，多选：A,B,C 判断：1-true 0-false")
    private String examAnswer;

    @ApiModelProperty("工种类型，多个使用，隔开")
    @NotEmpty(message = "工种类型不能为空")
    private String workType;

}
