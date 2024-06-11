package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.TbAccidentDTO;
import com.geovis.manager.bs.dto.TbAccidentSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbAccident;

/**
 * <p>
 * 事故事件管理 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbAccidentService extends IService<TbAccident> {

    /**
     * 保存事故
     *
     * @param dto
     * @return
     */
    TbAccident saveAccident(TbAccidentSaveOrUpdateDTO dto);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    TbAccidentDTO getAccidentById(String id);
}
