package com.geovis.manager.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemModuleResources;
import com.geovis.manager.system.mapper.SystemModuleResourcesMapper;
import com.geovis.manager.system.service.ISystemModuleResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统模块资源中间表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystemModuleResourcesServiceImpl extends ServiceImpl<SystemModuleResourcesMapper, SystemModuleResources> implements ISystemModuleResourcesService {

}
