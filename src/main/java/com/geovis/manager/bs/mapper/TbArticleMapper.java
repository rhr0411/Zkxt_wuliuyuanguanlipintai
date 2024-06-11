package com.geovis.manager.bs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geovis.manager.bs.dto.TbArticleWorkDTO;
import com.geovis.manager.bs.entity.TbArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章管理 Mapper 接口
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
public interface TbArticleMapper extends BaseMapper<TbArticle> {

    /**
     * 查询我的学习列表
     *
     * @param currentUserId
     * @param workerType
     * @return
     */
    List<TbArticleWorkDTO> selectExamList(@Param("currentUserId") String currentUserId, @Param("workerType") String workerType);
}
