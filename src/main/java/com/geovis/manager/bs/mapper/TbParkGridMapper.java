package com.geovis.manager.bs.mapper;

import com.geovis.manager.bs.dto.TbParkGridDangerNumDto;
import com.geovis.manager.bs.entity.TbParkGrid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 园区网格 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-19
 */
public interface TbParkGridMapper extends BaseMapper<TbParkGrid> {

    List<TbParkGridDangerNumDto> statistic();

    List<TbParkGridDangerNumDto> statisticHazard();
}
