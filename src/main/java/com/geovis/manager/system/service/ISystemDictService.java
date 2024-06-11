package com.geovis.manager.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.system.entity.SystemDict;
import com.geovis.manager.system.entity.SystemDictData;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author 王响
 * @since 2020-08-25
 */
public interface ISystemDictService extends IService<SystemDict> {

    /**
     * 批量删除
     *
     * @param idList 批量删除的id数组
     */
    void removeByIdList(Set<String> idList);

    /**
     * 根据编码获取字段数据
     *
     * @param code
     * @return
     */
    List<SystemDictData> listDictByCode(String code);

    /**
     * 根据编码获取字段数据,datakey 作为key
     *
     * @param code
     * @return
     */
    Map<String, SystemDictData> listDictMapByCode(String code);

}
