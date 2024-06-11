package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbParkGrid;
import com.geovis.manager.bs.mapper.TbParkGridMapper;
import com.geovis.manager.bs.service.ITbParkGridService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 园区网格 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-19
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbParkGridServiceImpl extends ServiceImpl<TbParkGridMapper, TbParkGrid> implements ITbParkGridService {

}
