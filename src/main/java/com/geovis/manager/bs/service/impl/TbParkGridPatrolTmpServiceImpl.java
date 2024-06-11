package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpDTO;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpSaveOrUpdateDTO;
import com.geovis.manager.bs.dto.TbParkGridPatrolTmpUserDTO;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmp;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmpUser;
import com.geovis.manager.bs.mapper.TbParkGridPatrolTmpMapper;
import com.geovis.manager.bs.service.ITbParkGridPatrolRecordService;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpService;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 园区网格_巡检模版 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbParkGridPatrolTmpServiceImpl extends ServiceImpl<TbParkGridPatrolTmpMapper, TbParkGridPatrolTmp> implements ITbParkGridPatrolTmpService {

    private final ITbParkGridPatrolTmpUserService parkGridPatrolTmpUserService;

    private final ITbParkGridPatrolRecordService parkGridPatrolRecordService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateEntity(TbParkGridPatrolTmpSaveOrUpdateDTO dto) {
        TbParkGridPatrolTmp entity = BeanUtil.copyProperties(dto, TbParkGridPatrolTmp.class);
        saveOrUpdate(entity);
        Assert.isFalse(CollUtil.isEmpty(dto.getPatrolFrequencyList()), "巡检频率不能为空");
        // 1、巡检频率时间校验并正序排序
        entity.setPatrolFrequency(CollUtil.join(patrolFrequencyValidatorSort(dto.getPatrolFrequencyList()), StrUtil.COMMA));
        // 2、删除原有成员
        parkGridPatrolTmpUserService.remove(Wrappers.lambdaUpdate(TbParkGridPatrolTmpUser.class).eq(TbParkGridPatrolTmpUser::getPatrolTmpId, entity.getId()));
        // 判断其他队伍是否有改队长，若有则报错
        long count = parkGridPatrolTmpUserService.count(Wrappers.lambdaQuery(TbParkGridPatrolTmpUser.class)
                .eq(TbParkGridPatrolTmpUser::getUserId, dto.getTeamLeaderId())
                .eq(TbParkGridPatrolTmpUser::getUserType, CommonConstants.YES));
        Assert.isFalse(count > 0, "该队长已在另一个队伍内担任队长，估无法担任此队队长");
        // 3、队长添加
        TbParkGridPatrolTmpUser leader = new TbParkGridPatrolTmpUser();
        leader.setPatrolTmpId(entity.getId())
                .setUserId(dto.getTeamLeaderId())
                .setUserType(CommonConstants.YES);
        parkGridPatrolTmpUserService.save(leader);
        // 4、成员添加
        if (CollUtil.isNotEmpty(dto.getUserIdList())) {
            dto.getUserIdList().forEach(userId -> {
                TbParkGridPatrolTmpUser user = new TbParkGridPatrolTmpUser();
                user.setPatrolTmpId(entity.getId())
                        .setUserId(userId)
                        .setUserType(CommonConstants.NO);
                parkGridPatrolTmpUserService.save(user);
            });
        }
        // 生成巡检记录 重置
        parkGridPatrolRecordService.refreshRecord(entity, dto.getTeamLeaderId());
    }

    /**
     * 巡检频率时间校验并正序排序
     *
     * @param patrolFrequencyList
     */
    public static List<String> patrolFrequencyValidatorSort(List<String> patrolFrequencyList) {
        Map<DateTime, String> map = MapUtil.newHashMap(true);
        // 1、每个时间段开始时间不能大于结束时间
        for (String patrolFrequency : patrolFrequencyList) {
            String[] split = patrolFrequency.split(StrUtil.DASHED);
            DateTime startTime = DateUtil.parse(split[0], "HH:mm");
            DateTime endTime = DateUtil.parse(split[1], "HH:mm");
            Assert.isFalse(startTime.compareTo(endTime) > 0, patrolFrequency + "该时间段开始时间不能大于结束时间！");
            map.put(startTime, patrolFrequency);
        }
        System.out.println(JSONUtil.toJsonStr(map));
        // 多个时间段时，进一步处理
        if (patrolFrequencyList.size() > 1) {
            // 2、按照开始时间进行排序
            map = CollUtil.sort(map, new Comparator<DateTime>() {
                @Override
                public int compare(DateTime o1, DateTime o2) {
                    return o1.compareTo(o2);
                }
            });
            System.out.println(JSONUtil.toJsonStr(map));
            // 3、每个时间段是否有交叉问题
            List<String> result = CollUtil.newArrayList(map.values());
            DateTime currentEndTime = DateUtil.parse((result.get(0).split(StrUtil.DASHED))[1], "HH:mm");
            for (int i = 1; i < result.size(); i++) {
                DateTime beginTime = DateUtil.parse((result.get(i).split(StrUtil.DASHED))[0], "HH:mm");
                Assert.isFalse(beginTime.compareTo(currentEndTime) < 0, StrUtil.format("【{}】与【{}】两个时间段不能交叉", result.get(i - 1), result.get(i)));
                currentEndTime = DateUtil.parse((result.get(i).split(StrUtil.DASHED))[1], "HH:mm");
            }
            return result;
        }
        return patrolFrequencyList;
    }

    @Override
    public List<TbParkGridPatrolTmpDTO> getList(TbParkGridPatrolTmp queryDTO) {
        LambdaQueryWrapper<TbParkGridPatrolTmp> wrapper = Wrappers.lambdaQuery(TbParkGridPatrolTmp.class).orderByDesc(TbParkGridPatrolTmp::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getGridId()), TbParkGridPatrolTmp::getGridId, queryDTO.getGridId())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getTeamName()), TbParkGridPatrolTmp::getTeamName, queryDTO.getTeamName())
                    .like(ObjectUtil.isNotEmpty(queryDTO.getTeamLeaderPhone()), TbParkGridPatrolTmp::getTeamLeaderPhone, queryDTO.getTeamLeaderPhone());
        }
        List<TbParkGridPatrolTmp> list = list(wrapper);
        List<TbParkGridPatrolTmpDTO> result = CollUtil.list(false);
        Map<String, List<TbParkGridPatrolTmpUserDTO>> userListMap = IterUtil.toListMap(parkGridPatrolTmpUserService.getList(null,null), TbParkGridPatrolTmpUserDTO::getPatrolTmpId);
        list.forEach(item -> {
            TbParkGridPatrolTmpDTO dto = BeanUtil.copyProperties(item, TbParkGridPatrolTmpDTO.class);
            setTeamUser(userListMap.get(dto.getId()), dto);
        });
        return result;
    }

    /**
     * 设置团队成员
     *
     * @param userList
     * @param dto
     */
    private void setTeamUser(List<TbParkGridPatrolTmpUserDTO> userList, TbParkGridPatrolTmpDTO dto) {
        if (CollUtil.isNotEmpty(userList)) {
            Iterator<TbParkGridPatrolTmpUserDTO> iterator = userList.iterator();
            while (iterator.hasNext()) {
                TbParkGridPatrolTmpUserDTO next = iterator.next();
                if (StrUtil.equals(CommonConstants.YES, next.getUserType())) {
                    dto.setTeamLeader(next);
                    iterator.remove();
                    break;
                }
            }
            dto.setUserList(userList);
        }
    }

    @Override
    public TbParkGridPatrolTmpDTO getEntityById(String id) {
        TbParkGridPatrolTmp entity = getById(id);
        if (ObjectUtil.isNotEmpty(entity)) {
            TbParkGridPatrolTmpDTO dto = BeanUtil.copyProperties(entity, TbParkGridPatrolTmpDTO.class);
            // 查询成员
            setTeamUser(parkGridPatrolTmpUserService.getList(id,null), dto);
            return dto;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        removeById(id);
        // 成员处理
        parkGridPatrolTmpUserService.remove(Wrappers.lambdaUpdate(TbParkGridPatrolTmpUser.class).eq(TbParkGridPatrolTmpUser::getPatrolTmpId, id));
    }

    @Override
    public TbParkGridPatrolTmpDTO getTmpDetailByLeaderId(String leaderId) {
        TbParkGridPatrolTmpUser leader = parkGridPatrolTmpUserService.getOne(Wrappers.lambdaQuery(TbParkGridPatrolTmpUser.class).eq(TbParkGridPatrolTmpUser::getUserId, leaderId).eq(TbParkGridPatrolTmpUser::getUserType, CommonConstants.YES));
        if (ObjectUtil.isEmpty(leader)) {
            return null;
        }
        TbParkGridPatrolTmp tmp = getById(leader.getPatrolTmpId());
        TbParkGridPatrolTmpDTO dto = BeanUtil.copyProperties(tmp, TbParkGridPatrolTmpDTO.class);
        // 查询所有成员
        setTeamUser(parkGridPatrolTmpUserService.getList(leader.getPatrolTmpId(), null), dto);
        return dto;
    }
}
