package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbExamResultDTO;
import com.geovis.manager.bs.dto.TbExamResultQueryDTO;
import com.geovis.manager.bs.entity.TbExamResult;

/**
 * <p>
 * 成绩管理和证书发放 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
public interface ITbExamResultService extends IService<TbExamResult> {

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<TbExamResultDTO> getPage(PageParam<TbExamResultQueryDTO> pageParam);

    /**
     * 最新考试成绩查询
     *
     * @return
     */
    TbExamResultDTO getLastExamResult();
}
