package com.geovis.manager.bs.mapper;

import com.geovis.manager.bs.dto.TbRiskHazardsSpecialStatisticDTO;
import com.geovis.manager.bs.entity.TbRiskHazardsSpecial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_特殊作业报备 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface TbRiskHazardsSpecialMapper extends BaseMapper<TbRiskHazardsSpecial> {

    List<TbRiskHazardsSpecialStatisticDTO> statisticTop5();
}
