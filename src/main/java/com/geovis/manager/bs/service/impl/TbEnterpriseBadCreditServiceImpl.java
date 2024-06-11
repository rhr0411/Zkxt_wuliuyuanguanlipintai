package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbEnterpriseBadCredit;
import com.geovis.manager.bs.mapper.TbEnterpriseBadCreditMapper;
import com.geovis.manager.bs.service.ITbEnterpriseBadCreditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 企业不良信用管理 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbEnterpriseBadCreditServiceImpl extends ServiceImpl<TbEnterpriseBadCreditMapper, TbEnterpriseBadCredit> implements ITbEnterpriseBadCreditService {

}
