package com.geovis.manager.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.mapper.SystemFileBusinessMapper;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 附件业务关联表
 * </p>
 *
 * @author zengds
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemFileBusinessServiceImpl extends ServiceImpl<SystemFileBusinessMapper, SystemFileBusiness> implements ISystemFileBusinessService {
}
