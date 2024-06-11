package com.geovis.manager.system.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.entity.SystemFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 附件表 Mapper 接口
 * </p>
 *
 * @author hhj
 * @since 2021-01-18
 */
public interface SystemFileMapper extends BaseMapper<SystemFile> {

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    List<SystemFileDTO> selectDataList(@Param(Constants.WRAPPER) QueryWrapper<Object> query);
}
