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
 * 考试题库_工种类别关联表
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_exam_question_work_type")
@ApiModel(value = "TbExamQuestionWorkType对象", description = "考试题库_工种类别关联表")
public class TbExamQuestionWorkType extends BaseEntity {

    @ApiModelProperty("考试题库ID")
    private String examQuestionId;

    @ApiModelProperty("工种类别：与第三方单位工种类别一致")
    private String workerType;


}
