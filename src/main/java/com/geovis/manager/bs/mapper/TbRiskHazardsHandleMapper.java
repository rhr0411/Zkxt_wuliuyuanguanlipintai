package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.bs.dto.RiskHazardsGridTop5StatisticDTO;
import com.geovis.manager.bs.dto.StatisticDTO;
import com.geovis.manager.bs.dto.TbRiskHazardsDTO;
import com.geovis.manager.bs.dto.TbRiskHazardsStatisticParamDTO;
import com.geovis.manager.bs.entity.TbRiskHazardsHandle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患整改 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface TbRiskHazardsHandleMapper extends BaseMapper<TbRiskHazardsHandle> {

    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<TbRiskHazardsDTO> selectRiskHazardsPage(@Param("page") IPage<TbRiskHazardsDTO> page, @Param(Constants.WRAPPER) QueryWrapper<Object> wrapper);

    /**
     * 月度隐患统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> selectYearMonthStatistic(@Param("dto") TbRiskHazardsStatisticParamDTO dto);

    /**
     * 行业重大隐患统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> selectIndustryStatistic(@Param("dto") TbRiskHazardsStatisticParamDTO dto);

    /**
     * 隐患级别统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> selectLevelStatistic(@Param("dto") TbRiskHazardsStatisticParamDTO dto);

    /**
     * 隐患企业所处网格统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> selectGridStatistic(@Param("dto") TbRiskHazardsStatisticParamDTO dto);

    /**
     *
     * @param year
     * @return
     */
    List<RiskHazardsGridTop5StatisticDTO> selectGridTop5Statistic(@Param("year") Integer year);
}
