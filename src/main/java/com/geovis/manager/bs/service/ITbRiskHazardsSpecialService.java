package com.geovis.manager.bs.service;

import com.geovis.manager.bs.dto.TbRiskHazardsSpecialStatisticDTO;
import com.geovis.manager.bs.entity.TbRiskHazardsSpecial;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_特殊作业报备 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbRiskHazardsSpecialService extends IService<TbRiskHazardsSpecial> {

    List<TbRiskHazardsSpecialStatisticDTO> statisticTop5();
}
