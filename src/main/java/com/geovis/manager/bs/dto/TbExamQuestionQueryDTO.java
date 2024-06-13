package com.geovis.manager.bs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 试题库查询
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamQuestionQueryDTO", description = "试题库查询")
public class TbExamQuestionQueryDTO implements Serializable {

    @ApiModelProperty("试题类型：1-单选 2-多选 3-判断")
    private String examType;

    @ApiModelProperty("工种类型")
    private String workerType;

}
