package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.system.entity.SystemDept;

import java.util.Set;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author hhj
 * @since 2021-01-18
 */
public interface ISystemDeptService extends IService<SystemDept> {

    /**
     * 批量删除
     *
     * @param idList 批量删除的id数组
     */
    void removeByIdList(Set<String> idList);

}
