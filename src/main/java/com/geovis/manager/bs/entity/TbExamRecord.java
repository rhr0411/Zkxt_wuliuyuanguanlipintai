package com.geovis.manager.bs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_exam_record")
@ApiModel(value = "TbExamRecord对象", description = "")
public class TbExamRecord extends BaseEntity {

    @ApiModelProperty("考生ID")
    private String userId;

    @ApiModelProperty("考试日期")
    private LocalDate examDate;

    @ApiModelProperty("考试得分")
    private Integer examScore;

    @ApiModelProperty("考试状态：1-已完成 0-未完成")
    private String status;

    @ApiModelProperty("考试结果：1-合格 0-不合格")
    private String result;

}
