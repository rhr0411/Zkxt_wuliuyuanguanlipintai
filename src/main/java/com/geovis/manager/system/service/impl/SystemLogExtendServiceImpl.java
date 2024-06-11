package com.geovis.manager.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemLogExtend;
import com.geovis.manager.system.mapper.SystemLogExtendMapper;
import com.geovis.manager.system.service.ISystemLogExtendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志扩展表
 * </p>
 *
 * @author 曾德实
 * @since 2022-10-10
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemLogExtendServiceImpl extends ServiceImpl<SystemLogExtendMapper, SystemLogExtend> implements ISystemLogExtendService {
}
