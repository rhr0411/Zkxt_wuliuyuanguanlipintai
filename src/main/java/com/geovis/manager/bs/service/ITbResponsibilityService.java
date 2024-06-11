package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbResponsibilityQueryDTO;
import com.geovis.manager.bs.entity.TbResponsibility;

/**
 * <p>
 * 责任清单管理 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */
public interface ITbResponsibilityService extends IService<TbResponsibility> {

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<TbResponsibility> getResponsibilityPage(PageParam<TbResponsibilityQueryDTO> pageParam);
}
