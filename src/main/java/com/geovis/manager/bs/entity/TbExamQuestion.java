package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考试题库
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_exam_question")
@ApiModel(value = "TbExamQuestion对象", description = "考试题库")
public class TbExamQuestion extends BaseEntity {

    @ApiModelProperty("试题类型：1-单选 2-多选 3-判断")
    private String examType;

    @ApiModelProperty("试题内容")
    private String examContent;

    @ApiModelProperty("试题选项：格式如{\"A\":\"值A\",\"B\":\"值B\"}")
    private String examSelect;

    @ApiModelProperty("试题答案：格式-》单选：A，多选：A,B,C 判断：1-true 0-false")
    private String examAnswer;


}
