package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbDangerChemical;
import com.geovis.manager.bs.mapper.TbDangerChemicalMapper;
import com.geovis.manager.bs.service.ITbDangerChemicalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 危险化学品 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbDangerChemicalServiceImpl extends ServiceImpl<TbDangerChemicalMapper, TbDangerChemical> implements ITbDangerChemicalService {

}
