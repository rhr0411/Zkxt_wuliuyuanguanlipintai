package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbParkGridPatrolRecord;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmp;

import java.util.List;

/**
 * <p>
 * 园区网格_巡检记录 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface ITbParkGridPatrolRecordService extends IService<TbParkGridPatrolRecord> {

    /**
     * 定时初始化记录
     */
    void initRecord();

    /**
     * 刷新巡检记录
     */
    void refreshRecord(TbParkGridPatrolTmp tmp, String leaderId);

    /**
     * 完成巡查
     *
     * @param dto
     */
    void completePatrol(TbParkGridPatrolCompleteDTO dto);

    /**
     * 详情查询
     *
     * @param id
     * @return
     */
    TbParkGridPatrolRecordDetailDTO getDetailById(String id);

    /**
     * 网格已完成数量统计
     *
     * @param year
     * @return
     */
    List<StatisticDTO> gridStatistic(Integer year);

    /**
     * 记录数量统计
     *
     * @param year
     * @return
     */
    TbParkGridPatrolRecordStsDTO numStatistic(Integer year);

    /**
     * 附件数量统计
     *
     * @param year
     * @return
     */
    TbParkGridPatrolRecordFileStsDTO fileStatistic(Integer year);
}
