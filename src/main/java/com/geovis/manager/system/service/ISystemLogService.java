package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.SystemLogQueryDTO;
import com.geovis.manager.system.dto.SystemLogSaveDTO;
import com.geovis.manager.system.entity.SystemLog;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author 王响
 * @since 2021-01-25
 */
public interface ISystemLogService extends IService<SystemLog> {

    /**
     * 异步保存
     *
     * @param saveDTO
     */
    void syncSave(SystemLogSaveDTO saveDTO);


    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    PageResult<SystemLog> listPage(PageParam<SystemLogQueryDTO> pageParam);

}
