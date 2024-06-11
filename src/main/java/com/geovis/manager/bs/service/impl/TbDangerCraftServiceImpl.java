package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbDangerCraft;
import com.geovis.manager.bs.mapper.TbDangerCraftMapper;
import com.geovis.manager.bs.service.ITbDangerCraftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 危险化工工艺 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbDangerCraftServiceImpl extends ServiceImpl<TbDangerCraftMapper, TbDangerCraft> implements ITbDangerCraftService {

}
