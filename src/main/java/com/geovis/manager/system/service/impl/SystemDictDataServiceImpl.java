package com.geovis.manager.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemDictData;
import com.geovis.manager.system.mapper.SystemDictDataMapper;
import com.geovis.manager.system.service.ISystemDictDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-08-25
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemDictDataServiceImpl extends ServiceImpl<SystemDictDataMapper, SystemDictData> implements ISystemDictDataService {
}
