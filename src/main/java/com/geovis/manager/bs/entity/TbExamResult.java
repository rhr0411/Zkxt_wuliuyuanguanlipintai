package com.geovis.manager.bs.entity;

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
 * 成绩管理和证书发放
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_exam_result")
@ApiModel(value = "TbExamResult对象", description = "成绩管理和证书发放")
public class TbExamResult extends BaseEntity {

    @ApiModelProperty("考生ID")
    private String userId;

    @ApiModelProperty("考试得分")
    private Integer examScore;

    @ApiModelProperty("最后考试日期")
    private LocalDate examDate;

    @ApiModelProperty("证书过期时间")
    private LocalDate certificateExpireDate;

}
