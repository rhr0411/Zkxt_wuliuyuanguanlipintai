package com.geovis.manager.bs.service;

import com.geovis.manager.bs.dto.TbDangerSourceStatisticBySourceDTO;
import com.geovis.manager.bs.dto.TbDangerSourceStatisticDTO;
import com.geovis.manager.bs.entity.TbDangerSource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 重大危险源 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbDangerSourceService extends IService<TbDangerSource> {

    TbDangerSourceStatisticDTO statisticByLevel();

    TbDangerSourceStatisticBySourceDTO statisticBySource();
}
