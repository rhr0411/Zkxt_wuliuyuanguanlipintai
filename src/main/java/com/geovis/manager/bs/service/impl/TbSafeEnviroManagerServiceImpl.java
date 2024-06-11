package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbSafeEnviroManager;
import com.geovis.manager.bs.mapper.TbSafeEnviroManagerMapper;
import com.geovis.manager.bs.service.ITbSafeEnviroManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 业务模块-安全与环境管理信息表 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbSafeEnviroManagerServiceImpl extends ServiceImpl<TbSafeEnviroManagerMapper, TbSafeEnviroManager> implements ITbSafeEnviroManagerService {

}
