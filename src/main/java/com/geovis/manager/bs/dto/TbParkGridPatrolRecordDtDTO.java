package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbParkGridPatrolRecordDt;
import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 园区网格_巡检记录明细对象
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbParkGridPatrolRecordDtDTO", description = "园区网格_巡检记录查询")
public class TbParkGridPatrolRecordDtDTO extends TbParkGridPatrolRecordDt {

    @ApiModelProperty("巡查附件")
    private List<SystemFileDTO> fileList;

}
