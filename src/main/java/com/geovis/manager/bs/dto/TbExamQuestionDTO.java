package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbExamQuestion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考试题库对象
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbExamQuestionDTO", description = "考试题库对象")
public class TbExamQuestionDTO extends TbExamQuestion {

    @ApiModelProperty("工种类型，多个用，隔开")
    private String workerType;

}
