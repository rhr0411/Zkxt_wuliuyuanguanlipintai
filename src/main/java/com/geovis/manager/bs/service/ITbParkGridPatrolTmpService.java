package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpDTO;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmp;

import java.util.List;

/**
 * <p>
 * 园区网格_巡检模版 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface ITbParkGridPatrolTmpService extends IService<TbParkGridPatrolTmp> {

    /**
     * 保存或更新
     *
     * @param dto
     */
    void saveOrUpdateEntity(TbParkGridPatrolTmpSaveOrUpdateDTO dto);

    /**
     * 列表查询
     *
     * @param queryDTO
     * @return
     */
    List<TbParkGridPatrolTmpDTO> getList(TbParkGridPatrolTmp queryDTO);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    TbParkGridPatrolTmpDTO getEntityById(String id);

    /**
     * 根据ID删除对象
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 查询详情
     *
     * @return
     */
    TbParkGridPatrolTmpDTO getTmpDetailByLeaderId(String leaderId);
}
