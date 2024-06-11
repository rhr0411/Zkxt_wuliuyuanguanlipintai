package com.geovis.manager.bs.service.impl;

import com.geovis.manager.bs.entity.TbArticleLearned;
import com.geovis.manager.bs.mapper.TbArticleLearnedMapper;
import com.geovis.manager.bs.service.ITbArticleLearnedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 文章管理_安全教育_已学习人员 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class TbArticleLearnedServiceImpl extends ServiceImpl<TbArticleLearnedMapper, TbArticleLearned> implements ITbArticleLearnedService {

}
