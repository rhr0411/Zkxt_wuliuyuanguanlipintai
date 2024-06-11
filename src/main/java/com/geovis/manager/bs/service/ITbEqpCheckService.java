package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.TbEqpCheckDTO;
import com.geovis.manager.bs.dto.TbEqpCheckSaveOrUpdateDTO;
import com.geovis.manager.bs.dto.TbEqpCheckStatisticDTO;
import com.geovis.manager.bs.entity.TbEqpCheck;

/**
 * <p>
 * 装置开停车和大检修管理 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbEqpCheckService extends IService<TbEqpCheck> {

    /**
     * 保存或更新
     *
     * @param dto
     * @return
     */
    TbEqpCheck saveOrUpdateEntity(TbEqpCheckSaveOrUpdateDTO dto);

    /**
     * 明细查询
     *
     * @param id
     * @return
     */
    TbEqpCheckDTO getEntityById(String id);

    /**
     * 统计
     *
     * @return
     */
    TbEqpCheckStatisticDTO statistic();
}
