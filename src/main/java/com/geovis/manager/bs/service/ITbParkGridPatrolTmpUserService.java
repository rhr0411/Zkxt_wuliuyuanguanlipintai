package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpUserDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmpUser;

import java.util.List;

/**
 * <p>
 * 园区网格_巡查模版_成员表 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface ITbParkGridPatrolTmpUserService extends IService<TbParkGridPatrolTmpUser> {

    /**
     * 查询列表
     * key patrolTmpId
     *
     * @return
     */
    List<TbParkGridPatrolTmpUserDTO> getList(String patrolTmpId, String userType);
}
