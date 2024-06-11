package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpUserDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmpUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 园区网格_巡查模版_成员表 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface TbParkGridPatrolTmpUserMapper extends BaseMapper<TbParkGridPatrolTmpUser> {

    /**
     * 查询所有
     *
     * @return
     */
    List<TbParkGridPatrolTmpUserDTO> selectListDTO(@Param("patrolTmpId") String patrolTmpId, @Param("userType") String userType);
}
