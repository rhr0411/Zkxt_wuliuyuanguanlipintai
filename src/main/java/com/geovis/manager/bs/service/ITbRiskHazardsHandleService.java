package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbRiskHazardsHandle;

import java.util.List;

/**
 * <p>
 * 风险隐患管理_隐患整改 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbRiskHazardsHandleService extends IService<TbRiskHazardsHandle> {

    /**
     * 移动端-隐患上报
     *
     * @param dto
     */
    TbRiskHazardsHandle appSave(TbRiskHazardsAppSaveDTO dto);

    /**
     * 园区端-隐患督办
     *
     * @param dto
     * @return
     */
    TbRiskHazardsHandle supervision(TbRiskHazardsSupervisionDTO dto);

    /**
     * 企业端-隐患整改
     *
     * @param dto
     * @return
     */
    TbRiskHazardsHandle handle(TbRiskHazardsHandleDTO dto);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    TbRiskHazardsDTO getRiskHazardsById(String id);

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<TbRiskHazardsDTO> getRiskHazardsPage(PageParam<TbRiskHazardsQueryDTO> pageParam);


    /**
     * 园区端-隐患新增并督办
     *
     * @param dto
     * @return
     */
    TbRiskHazardsHandle parkSaveAndSupervision(TbRiskHazardsAddSupervisionDTO dto);

    /**
     * 园区端-隐患复查
     *
     * @param dto
     * @return
     */
    TbRiskHazardsHandle check(TbRiskHazardsCheckDTO dto);

    /**
     * 月度隐患统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> yearMonthStatistic(TbRiskHazardsStatisticParamDTO dto);

    /**
     * 行业重大隐患统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> industryStatistic(TbRiskHazardsStatisticParamDTO dto);

    /**
     * 隐患级别统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> levelStatistic(TbRiskHazardsStatisticParamDTO dto);

    /**
     * 隐患整改状态统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> statusStatistic(TbRiskHazardsStatisticParamDTO dto);

    /**
     * 隐患企业所处网格统计
     *
     * @param dto
     * @return
     */
    List<StatisticDTO> gridStatistic(TbRiskHazardsStatisticParamDTO dto);

    /**
     * 隐患网格top5
     *
     * @param year
     * @return
     */
    List<RiskHazardsGridTop5StatisticDTO> gridTop5Statistic(Integer year);
}
