package com.geovis.manager.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geovis.manager.system.entity.SystemRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 曾德实
 * @since 2023-07-20
 */
public interface SystemRegionMapper extends BaseMapper<SystemRegion> {

    /**
     * 查询列表（父类ID，自动递归查询列表）
     * @param idList
     * @return
     */
    List<SystemRegion> selectListByParent(@Param("idList") List<String> idList);
}
