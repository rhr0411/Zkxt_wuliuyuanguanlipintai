package com.geovis.manager.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geovis.manager.system.entity.SystemDict;
import com.geovis.manager.system.entity.SystemDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author 王响
 * @since 2020-08-25
 */
public interface SystemDictMapper extends BaseMapper<SystemDict> {

    /**
     * 根据编码获取字典数据
     *
     * @param code
     * @return
     */
    List<SystemDictData> selectByCode(@Param("code") String code);


}
