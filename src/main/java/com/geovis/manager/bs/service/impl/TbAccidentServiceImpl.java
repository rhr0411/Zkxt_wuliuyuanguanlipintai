package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.manager.bs.constant.AccidentConstant;
import com.geovis.manager.bs.dto.TbAccidentDTO;
import com.geovis.manager.bs.dto.TbAccidentSaveOrUpdateDTO;
import com.geovis.manager.bs.entity.TbAccident;
import com.geovis.manager.bs.mapper.TbAccidentMapper;
import com.geovis.manager.bs.service.ITbAccidentService;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFileBusiness;
import com.geovis.manager.system.service.ISystemFileBusinessService;
import com.geovis.manager.system.service.ISystemFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 事故事件管理 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-15
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbAccidentServiceImpl extends ServiceImpl<TbAccidentMapper, TbAccident> implements ITbAccidentService {

    private final ISystemFileBusinessService systemFileBusinessService;

    private final ISystemFileService systemFileService;

    @Override
    public TbAccident saveAccident(TbAccidentSaveOrUpdateDTO dto) {
        TbAccident entity = BeanUtil.copyProperties(dto, TbAccident.class);
        saveOrUpdate(entity);
        // 附件处理
        systemFileBusinessService.remove(Wrappers.lambdaUpdate(SystemFileBusiness.class)
                .in(SystemFileBusiness::getParam1, AccidentConstant.FILE_FLAG_ACCIDENT_FILE, AccidentConstant.FILE_FLAG_ACCIDENT_IMAGE)
                .eq(SystemFileBusiness::getParam2, dto.getEnterpriseId()));
        if (CollUtil.isNotEmpty(dto.getFileIdList())) {
            dto.getFileIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(AccidentConstant.FILE_FLAG_ACCIDENT_FILE)
                        .setParam2(dto.getEnterpriseId())
                ;
                systemFileBusinessService.save(business);
            });
        }
        if (CollUtil.isNotEmpty(dto.getImagesIdList())) {
            dto.getImagesIdList().forEach(fileId -> {
                SystemFileBusiness business = new SystemFileBusiness();
                business.setFileId(fileId)
                        .setParam1(AccidentConstant.FILE_FLAG_ACCIDENT_IMAGE)
                        .setParam2(dto.getEnterpriseId())
                ;
                systemFileBusinessService.save(business);
            });
        }
        return entity;
    }

    @Override
    public TbAccidentDTO getAccidentById(String id) {
        TbAccident entity = getById(id);
        if (ObjectUtil.isNotEmpty(entity)) {
            TbAccidentDTO result = BeanUtil.copyProperties(entity, TbAccidentDTO.class);
            // 查询附件列表AccidentConstant.FILE_FLAG_ACCIDENT_FILE, AccidentConstant.FILE_FLAG_ACCIDENT_IMAGE
            SystemFileQueryDTO queryDTO = new SystemFileQueryDTO();
            queryDTO.setParam1(AccidentConstant.FILE_FLAG_ACCIDENT_FILE)
                    .setParam2(result.getEnterpriseId());
            List<SystemFileDTO> list = systemFileService.getList(queryDTO);
            result.setFileList(list);
            queryDTO = new SystemFileQueryDTO();
            queryDTO.setParam1(AccidentConstant.FILE_FLAG_ACCIDENT_IMAGE)
                    .setParam2(result.getEnterpriseId());
            list = systemFileService.getList(queryDTO);
            result.setImageList(list);
            return result;
        }
        return null;
    }
}
