package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.bs.mapper.TbEnterpriseMapper;
import com.geovis.manager.bs.service.ITbEnterpriseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-08
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbEnterpriseServiceImpl extends ServiceImpl<TbEnterpriseMapper, TbEnterprise> implements ITbEnterpriseService {

}
