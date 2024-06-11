package com.geovis.manager.bs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.bs.dto.TbArticleWorkDTO;
import com.geovis.manager.bs.entity.TbArticle;

import java.util.List;

/**
 * <p>
 * 文章管理 服务类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface ITbArticleService extends IService<TbArticle> {

    /**
     * 查询我的考试培训列表
     *
     * @return
     */
    List<TbArticleWorkDTO> getMyExamList();
}
