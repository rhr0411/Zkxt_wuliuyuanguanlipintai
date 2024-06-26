package com.geovis.manager.bs.service;

import com.geovis.manager.bs.dto.TbRiskHazardsStdTree;
import com.geovis.manager.bs.entity.TbRiskHazardsStd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_排查标准 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface ITbRiskHazardsStdService extends IService<TbRiskHazardsStd> {

    List<TbRiskHazardsStdTree> getTree();
}
