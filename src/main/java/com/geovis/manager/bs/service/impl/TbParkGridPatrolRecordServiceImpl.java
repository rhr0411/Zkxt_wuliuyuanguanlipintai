package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.util.NumberUtil;
import com.geovis.manager.bs.constant.ParkGridPatrolRecordDtConstant;
import com.geovis.manager.bs.dto.*;
import com.geovis.manager.bs.entity.TbParkGrid;
import com.geovis.manager.bs.entity.TbParkGridPatrolRecord;
import com.geovis.manager.bs.entity.TbParkGridPatrolRecordDt;
import com.geovis.manager.bs.entity.TbParkGridPatrolTmp;
import com.geovis.manager.bs.mapper.TbParkGridPatrolRecordMapper;
import com.geovis.manager.bs.mapper.TbParkGridPatrolTmpMapper;
import com.geovis.manager.bs.service.ITbParkGridPatrolRecordDtService;
import com.geovis.manager.bs.service.ITbParkGridPatrolRecordService;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpUserService;
import com.geovis.manager.bs.service.ITbParkGridService;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFile;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import com.geovis.manager.system.service.ISystemFileService;
import com.geovis.manager.system.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 园区网格_巡检记录 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbParkGridPatrolRecordServiceImpl extends ServiceImpl<TbParkGridPatrolRecordMapper, TbParkGridPatrolRecord> implements ITbParkGridPatrolRecordService {

    private final ITbParkGridPatrolRecordDtService parkGridPatrolRecordDtService;

    private final ITbParkGridService parkGridService;

    private final ISystemFileService systemFileService;

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemUserService systemUserService;

    private final TbParkGridPatrolTmpMapper tbParkGridPatrolTmpMapper;

    private final ITbParkGridPatrolTmpUserService parkGridPatrolTmpUserService;

    public static List<String> imageExtNameList = CollUtil.newArrayList("jpeg", "jpg", "png", "gif");

    public static List<String> videoExtNameList = CollUtil.newArrayList("avi", "mp4", "mov", "mkv", "wmv");

    @Override
    public void initRecord() {
        log.info("========================= 开始初始化【园区网格_巡检记录】  =========================");
        // 查询所有模版信息
        List<TbParkGridPatrolTmp> tmpList = tbParkGridPatrolTmpMapper.selectList(Wrappers.lambdaQuery(TbParkGridPatrolTmp.class));
        // 查询所有网格信息
        Map<String, TbParkGrid> parkGridMap = IterUtil.toMap(parkGridService.list(), TbParkGrid::getId);
        // 查询所有队长
        Map<String, TbParkGridPatrolTmpUserDTO> leaderDTOMap = IterUtil.toMap(parkGridPatrolTmpUserService.getList(null, CommonConstants.YES), TbParkGridPatrolTmpUserDTO::getPatrolTmpId);
        // 园区网格巡查记录列表
        List<TbParkGridPatrolRecord> list = CollUtil.list(false);
        for (TbParkGridPatrolTmp tmp : tmpList) {
            // 模版生成记录
            TbParkGrid tbParkGrid = parkGridMap.get(tmp.getGridId());
            TbParkGridPatrolTmpUserDTO leader = leaderDTOMap.get(tmp.getId());
            String[] split = tmp.getPatrolFrequency().split(StrUtil.COMMA);
            for (String frequency : split) {
                String[] frequencySplit = frequency.split(StrUtil.DASHED);
                TbParkGridPatrolRecord record = BeanUtil.copyProperties(tmp, TbParkGridPatrolRecord.class);
                record.setPatrolTmpId(tmp.getId())
                        .setTeamLeaderId(leader.getUserId())
                        .setTeamLeaderName(leader.getUserName())
                        .setPatrolStatus(CommonConstants.NO)
                        .setGridName(tbParkGrid.getName())
                        .setRecordNum(0)
                        .setPatrolPlanStartTime(LocalDateTimeUtil.parse(DateUtil.formatDateTime(DateUtil.parseTimeToday(frequencySplit[0]))))
                        .setPatrolPlanEndTime(LocalDateTimeUtil.parse(DateUtil.formatDateTime(DateUtil.parseTimeToday(frequencySplit[1]))));
                list.add(record);
            }
        }
        saveBatch(list);
        log.info("========================= 结束初始化【园区网格_巡检记录】  =========================");
    }

    @Override
    public void refreshRecord(TbParkGridPatrolTmp tmp, String leaderId) {
        // 删除未完成巡查的数据
        remove(Wrappers.lambdaUpdate(TbParkGridPatrolRecord.class).eq(TbParkGridPatrolRecord::getPatrolStatus, CommonConstants.NO).eq(TbParkGridPatrolRecord::getPatrolTmpId, tmp.getId()));
        // 查询已完成的，避免再次生成
        List<TbParkGridPatrolRecord> list = list(Wrappers.lambdaQuery(TbParkGridPatrolRecord.class).eq(TbParkGridPatrolRecord::getPatrolTmpId, tmp.getId()));
        Map<String, String> hasInitMap = MapUtil.newHashMap();
        list.forEach(item -> {
            hasInitMap.put(LocalDateTimeUtil.formatNormal(item.getPatrolPlanStartTime()) + LocalDateTimeUtil.formatNormal(item.getPatrolPlanEndTime()), CommonConstants.YES);
        });
        // 查询网格
        TbParkGrid grid = parkGridService.getById(tmp.getGridId());
        SystemUser leader = systemUserService.getById(leaderId);
        String[] split = tmp.getPatrolFrequency().split(StrUtil.COMMA);
        for (String frequency : split) {
            String[] frequencySplit = frequency.split(StrUtil.DASHED);
            TbParkGridPatrolRecord record = BeanUtil.copyProperties(tmp, TbParkGridPatrolRecord.class);
            record.setPatrolTmpId(tmp.getId())
                    .setTeamLeaderId(leader.getId())
                    .setTeamLeaderName(leader.getRealName())
                    .setGridName(grid.getName())
                    .setPatrolStatus(CommonConstants.NO)
                    .setPatrolPlanStartTime(LocalDateTimeUtil.parse(DateUtil.formatDateTime(DateUtil.parseTimeToday(frequencySplit[0]))))
                    .setPatrolPlanEndTime(LocalDateTimeUtil.parse(DateUtil.formatDateTime(DateUtil.parseTimeToday(frequencySplit[1]))));
            if (!hasInitMap.containsKey(LocalDateTimeUtil.formatNormal(record.getPatrolPlanStartTime()) + LocalDateTimeUtil.formatNormal(record.getPatrolPlanEndTime()))) {
                save(record);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completePatrol(TbParkGridPatrolCompleteDTO dto) {
        TbParkGridPatrolRecord record = getById(dto.getId());
        Assert.isFalse(ObjectUtil.isEmpty(record), "记录信息查询为空");
        record.setPatrolActualEndTime(LocalDateTime.now())
                .setPatrolActualRoute(dto.getPatrolActualRoute())
                .setPatrolStatus(CommonConstants.YES)
                .setRecordNum(CollUtil.isEmpty(dto.getList()) ? 0 : dto.getList().size());
        updateById(record);
        if (CollUtil.isEmpty(dto.getList())) {
            return;
        }
        // 记录处理
        List<SystemFileBusiness> fileBusinessList = CollUtil.list(false);
        Set<String> fileIdSet = CollUtil.newHashSet();
        dto.getList().forEach(contentDto -> {
            // 记录明细
            TbParkGridPatrolRecordDt dt = new TbParkGridPatrolRecordDt();
            dt.setPatrolContent(contentDto.getPatrolContent())
                    .setPatrolRecordId(record.getId());
            parkGridPatrolRecordDtService.save(dt);
            // 记录明细的附件处理
            if (CollUtil.isNotEmpty(contentDto.getFileIdList())) {
                contentDto.getFileIdList().forEach(fileId -> {
                    fileIdSet.add(fileId);
                    SystemFileBusiness fileBusiness = new SystemFileBusiness();
                    fileBusiness.setFileId(fileId)
                            .setParam1(record.getId())
                            .setParam2(dt.getId())
                            // 默认是附件类型
                            .setParam3(ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD_FILE)
                            .setParam4(ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD);
                    fileBusinessList.add(fileBusiness);
                });
            }
        });
        if (CollUtil.isEmpty(fileBusinessList)) {
            return;
        }
        Map<String, String> fileMap = IterUtil.toMap(systemFileService.listByIds(fileIdSet), SystemFile::getId, SystemFile::getExtName);
        // 附件文件格式处理
        fileBusinessList.forEach(systemFileBusiness -> {
            String extName = fileMap.get(systemFileBusiness.getFileId());
            if (StrUtil.isNotEmpty(extName) && imageExtNameList.contains(extName.toLowerCase())) {
                systemFileBusiness.setParam3(ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD_IMAGE);
                return;
            }
            if (StrUtil.isNotEmpty(extName) && videoExtNameList.contains(extName.toLowerCase())) {
                systemFileBusiness.setParam3(ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD_VIDEO);
                return;
            }
        });

        systemFileBusinessService.saveBatch(fileBusinessList);
    }

    @Override
    public TbParkGridPatrolRecordDetailDTO getDetailById(String id) {
        TbParkGridPatrolRecord record = getById(id);
        if (ObjectUtil.isNotEmpty(record)) {
            TbParkGridPatrolRecordDetailDTO dto = BeanUtil.copyProperties(record, TbParkGridPatrolRecordDetailDTO.class);
            // 查询问题记录列表
            List<TbParkGridPatrolRecordDt> list = parkGridPatrolRecordDtService.list(Wrappers.lambdaQuery(TbParkGridPatrolRecordDt.class)
                    .eq(TbParkGridPatrolRecordDt::getPatrolRecordId, dto.getId()));
            // 查询附件
            SystemFileQueryDTO queryDTO = new SystemFileQueryDTO();
            queryDTO.setParam1(dto.getId());
            Map<String, List<SystemFileDTO>> fileListMap = IterUtil.toListMap(systemFileService.getList(queryDTO), SystemFileDTO::getParam2);
            List<TbParkGridPatrolRecordDtDTO> recordList = CollUtil.list(false);
            for (TbParkGridPatrolRecordDt tbParkGridPatrolRecordDt : list) {
                TbParkGridPatrolRecordDtDTO tbParkGridPatrolRecordDtDTO = BeanUtil.copyProperties(tbParkGridPatrolRecordDt, TbParkGridPatrolRecordDtDTO.class);
                tbParkGridPatrolRecordDtDTO.setFileList(fileListMap.get(tbParkGridPatrolRecordDtDTO.getId()));
                recordList.add(tbParkGridPatrolRecordDtDTO);
            }
            dto.setRecordList(recordList);
            return dto;
        }
        return null;
    }

    @Override
    public List<StatisticDTO> gridStatistic(Integer year) {
        return baseMapper.selectGridStatistic(year);
    }

    @Override
    public TbParkGridPatrolRecordStsDTO numStatistic(Integer year) {
        TbParkGridPatrolRecordStsDTO dto = new TbParkGridPatrolRecordStsDTO();
        dto.setTotalNum((int) count()).setCompleteNum((int) count(Wrappers.lambdaQuery(TbParkGridPatrolRecord.class).eq(TbParkGridPatrolRecord::getPatrolStatus, CommonConstants.YES)));
        if(dto.getTotalNum()==0)
        {
            dto.setCompleteRate(new BigDecimal("0.00"));
        }else {
            dto.setCompleteRate(NumberUtil.div(dto.getCompleteNum(), dto.getTotalNum(), 2));
        }

        return dto;
    }

    @Override
    public TbParkGridPatrolRecordFileStsDTO fileStatistic(Integer year) {
        TbParkGridPatrolRecordFileStsDTO dto = new TbParkGridPatrolRecordFileStsDTO();
        List<SystemFileBusiness> list = systemFileBusinessService.list(Wrappers.lambdaQuery(SystemFileBusiness.class)
                .eq(SystemFileBusiness::getParam4, ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD)
                .apply(StrUtil.format("date_part('year',create_time) = '{}'", year)));

        for (SystemFileBusiness systemFileBusiness : list) {
            if (StrUtil.equals(ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD_IMAGE, systemFileBusiness.getParam3())) {
                dto.setImageNum(dto.getImageNum() + 1);
            }
            if (StrUtil.equals(ParkGridPatrolRecordDtConstant.FILE_FLAG_PATROL_RECORD_VIDEO, systemFileBusiness.getParam3())) {
                dto.setVideoNum(dto.getVideoNum() + 1);
            }
        }
        return dto;
    }

    public static void main(String[] args) {
        String aa = "15:11";
        DateTime dateTime = DateUtil.parseTimeToday(aa);
        System.out.println(dateTime);
    }
}
