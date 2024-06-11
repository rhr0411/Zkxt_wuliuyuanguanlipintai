package com.geovis.manager.bs.mapper;

import com.geovis.manager.bs.dto.StatisticDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 园区网格_巡检记录 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface TbParkGridPatrolRecordMapper extends BaseMapper<TbParkGridPatrolRecord> {

    /**
     * 网格已完成数量统计
     * @param year
     * @return
     */
    List<StatisticDTO> selectGridStatistic(@Param("year") Integer year);
}
