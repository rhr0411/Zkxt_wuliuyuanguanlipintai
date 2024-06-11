package com.geovis.manager.bs.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 园区网格保存或更新
 * </p>
 *
 * @author zengds
 * @since 2024-04-19
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridSaveOrUpdateDTO", description = "园区网格保存或更新")
public class TbParkGridSaveOrUpdateDTO implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("网格名称")
    @NotEmpty(message = "网格名称不能为空")
    private String name;

    @ApiModelProperty("坐标点区域")
    @NotEmpty(message = "坐标点区域不能为空")
    private String coordinatePoints;

    @ApiModelProperty("关联企业ID列表")
    private List<String> enterpriseIdList;


}
