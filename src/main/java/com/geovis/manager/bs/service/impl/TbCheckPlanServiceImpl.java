package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbCheckPlan;
import com.geovis.manager.bs.mapper.TbCheckPlanMapper;
import com.geovis.manager.bs.service.ITbCheckPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 检查计划 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbCheckPlanServiceImpl extends ServiceImpl<TbCheckPlanMapper, TbCheckPlan> implements ITbCheckPlanService {

}
