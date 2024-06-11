package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbMessage;
import com.geovis.manager.bs.mapper.TbMessageMapper;
import com.geovis.manager.bs.service.ITbMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 消息提醒 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-05-06
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbMessageServiceImpl extends ServiceImpl<TbMessageMapper, TbMessage> implements ITbMessageService {

}
