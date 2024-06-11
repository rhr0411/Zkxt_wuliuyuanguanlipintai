package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbParkGridPatrolRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 园区网格_巡检记录名称查询
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolRecordDetailDTO", description = "园区网格_巡检记录查询")
public class TbParkGridPatrolRecordDetailDTO extends TbParkGridPatrolRecord {

    @ApiModelProperty("巡查计划对象")
    private TbParkGridPatrolTmpDTO tmpDTO;

    @ApiModelProperty("巡查问题记录列表")
    private List<TbParkGridPatrolRecordDtDTO> recordList;

}
