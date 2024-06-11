package com.geovis.manager.bs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpUserDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmpUser;
import com.geovis.manager.bs.mapper.TbParkGridPatrolTmpUserMapper;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 园区网格_巡查模版_成员表 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbParkGridPatrolTmpUserServiceImpl extends ServiceImpl<TbParkGridPatrolTmpUserMapper, TbParkGridPatrolTmpUser> implements ITbParkGridPatrolTmpUserService {

    @Override
    public List<TbParkGridPatrolTmpUserDTO> getList(String patrolTmpId, String userType) {
        return baseMapper.selectListDTO(patrolTmpId,userType);
    }
}
