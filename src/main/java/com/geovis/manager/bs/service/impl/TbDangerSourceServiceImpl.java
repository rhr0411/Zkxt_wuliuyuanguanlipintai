package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbDangerSource;
import com.geovis.manager.bs.mapper.TbDangerSourceMapper;
import com.geovis.manager.bs.service.ITbDangerSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 重大危险源 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbDangerSourceServiceImpl extends ServiceImpl<TbDangerSourceMapper, TbDangerSource> implements ITbDangerSourceService {

}
