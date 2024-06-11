package com.geovis.manager.bs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.auth.util.AuthUtil;
import com.geovis.manager.bs.dto.TbArticleWorkDTO;
import com.geovis.manager.bs.entity.TbArticle;
import com.geovis.manager.bs.mapper.TbArticleMapper;
import com.geovis.manager.bs.service.ITbArticleService;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章管理 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbArticleServiceImpl extends ServiceImpl<TbArticleMapper, TbArticle> implements ITbArticleService {

    private final ISystemUserService systemUserService;

    @Override
    public List<TbArticleWorkDTO> getMyExamList() {
        // 查询当前登录人所在第三方单位的工种
        SystemUser user = systemUserService.getById(AuthUtil.getCurrentUserId());
        List<TbArticleWorkDTO> list = baseMapper.selectExamList(AuthUtil.getCurrentUserId(), user.getWorkerType());
        return list;
    }
}
