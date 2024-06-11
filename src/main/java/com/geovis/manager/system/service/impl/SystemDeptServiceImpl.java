package com.geovis.manager.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.system.entity.SystemDept;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.mapper.SystemDeptMapper;
import com.geovis.manager.system.service.ISystemDeptService;
import com.geovis.manager.system.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author hhj
 * @since 2021-01-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystemDeptServiceImpl extends ServiceImpl<SystemDeptMapper, SystemDept> implements ISystemDeptService {

    private final ISystemUserService systemUserService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeByIdList(Set<String> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        //1、刪除本身
        removeByIds(idList);

        //2、删除用户绑定的机构id
        systemUserService.remove(new UpdateWrapper<SystemUser>().lambda().in(SystemUser::getDeptId, idList));

        //3、刪除孩子节点
        List<SystemDept> list = baseMapper.selectList(Wrappers.lambdaQuery(SystemDept.class).select(SystemDept::getId).in(SystemDept::getParentId, idList));
        Set<String> childrenIdList = CollUtil.set(false);
        list.forEach(systemDept -> childrenIdList.add(systemDept.getId()));
        removeByIdList(childrenIdList);

    }
}
