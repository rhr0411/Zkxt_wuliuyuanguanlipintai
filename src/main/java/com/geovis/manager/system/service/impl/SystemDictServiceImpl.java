package com.geovis.manager.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemDict;
import com.geovis.manager.system.entity.SystemDictData;
import com.geovis.manager.system.mapper.SystemDictDataMapper;
import com.geovis.manager.system.mapper.SystemDictMapper;
import com.geovis.manager.system.service.ISystemDictService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author 王响
 * @since 2020-08-25
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemDictServiceImpl extends ServiceImpl<SystemDictMapper, SystemDict> implements ISystemDictService {

    private final SystemDictDataMapper systemDictDataMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeByIdList(Set<String> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        //1、刪除本身
        removeByIds(idList);

        systemDictDataMapper.delete(Wrappers.lambdaQuery(SystemDictData.class).in(SystemDictData::getDictId, idList));
    }

    @Override
    public List<SystemDictData> listDictByCode(String code) {
        List<SystemDictData> result = CollUtil.list(false);
        if (StrUtil.isEmpty(code)) {
            return result;
        }
        result = baseMapper.selectByCode(code);
        return result;
    }

    @Override
    public Map<String, SystemDictData> listDictMapByCode(String code) {
        List<SystemDictData> list = listDictByCode(code);
        return IterUtil.toMap(list, SystemDictData::getDataKey);
    }
}
