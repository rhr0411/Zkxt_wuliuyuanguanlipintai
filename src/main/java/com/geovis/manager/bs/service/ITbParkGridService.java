package com.geovis.manager.bs.service;

import com.geovis.manager.bs.dto.TbParkGridDangerNumDto;
import com.geovis.manager.bs.entity.TbParkGrid;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 园区网格 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-19
 */
public interface ITbParkGridService extends IService<TbParkGrid> {
    List<TbParkGridDangerNumDto> statistic();

    List<TbParkGridDangerNumDto> statisticHazard();
}
