package com.geovis.manager.bs.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbResponsibilityQueryDTO;
import com.geovis.manager.bs.entity.TbResponsibility;
import com.geovis.manager.bs.mapper.TbResponsibilityMapper;
import com.geovis.manager.bs.service.ITbResponsibilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 责任清单管理 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbResponsibilityServiceImpl extends ServiceImpl<TbResponsibilityMapper, TbResponsibility> implements ITbResponsibilityService {

    @Override
    public PageResult<TbResponsibility> getResponsibilityPage(PageParam<TbResponsibilityQueryDTO> pageParam) {
        IPage<TbResponsibility> page = pageParam.buildPage();
        TbResponsibilityQueryDTO queryDTO = pageParam.getQuery();
        QueryWrapper<Object> wrapper = Wrappers.query();
        wrapper.eq("1",1).orderByDesc("a.update_time");
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getEnterpriseId()), "a.enterprise_id", queryDTO.getEnterpriseId())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getUploadTimeStart()), "b.create_time", queryDTO.getUploadTimeStart())
                    .ge(ObjectUtil.isNotEmpty(queryDTO.getUploadTimeEnd()), "b.create_time", queryDTO.getUploadTimeEnd());
        }
        baseMapper.selectResponsibilityPage(page, wrapper);
        return new PageResult(page);
    }
}
