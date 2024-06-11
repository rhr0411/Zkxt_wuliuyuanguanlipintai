package com.geovis.manager.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.exception.BizException;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFile;
import com.geovis.manager.system.mapper.SystemFileMapper;
import com.geovis.manager.system.props.FileProperties;
import com.geovis.manager.system.service.ISystemFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 附件表 服务
 * </p>
 *
 * @author zengds
 */
@Service
@AllArgsConstructor
@Slf4j
public class SystemFileServiceImpl extends ServiceImpl<SystemFileMapper, SystemFile> implements ISystemFileService {

    private final FileProperties fileProperties;

    @Override
    public List<SystemFileDTO> getList(SystemFileQueryDTO dto) {
        QueryWrapper<Object> query = Wrappers.query();
        if (ObjectUtil.isNotEmpty(dto)) {
            query.like(StrUtil.isNotEmpty(dto.getName()), "a.name", dto.getName())
                    .eq(StrUtil.isNotEmpty(dto.getId()), "a.id", dto.getId())
                    .eq(StrUtil.isNotEmpty(dto.getFileId()), "b.file_id", dto.getFileId())
                    .eq(StrUtil.isNotEmpty(dto.getParam1()), "b.param1", dto.getParam1())
                    .eq(StrUtil.isNotEmpty(dto.getParam2()), "b.param2", dto.getParam2())
                    .eq(StrUtil.isNotEmpty(dto.getParam3()), "b.param3", dto.getParam3())
                    .eq(StrUtil.isNotEmpty(dto.getParam4()), "b.param4", dto.getParam4())
                    .eq(StrUtil.isNotEmpty(dto.getParam5()), "b.param5", dto.getParam5());
        }
        return baseMapper.selectDataList(query);
    }

    @Override
    public SystemFile upload(MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            log.error("没有该文件！");
            throw new BizException("没有该文件！");
        }
        if (StrUtil.isEmpty(fileProperties.getUploadDir())) {
            fileProperties.setUploadDir(SystemUtil.get(SystemUtil.USER_DIR));
        }

        // 文件存储根路径
        String rootDir = StrUtil.replace(fileProperties.getUploadDir(), "\\", "/");

        // 文件存储路径
        String pathDir = StrUtil.format("/{}/{}/", fileProperties.getPrefixDir(), DateUtil.format(new Date(), "yyyy/MM/dd/HH"));

        // 文件名称
        String fileName = StrUtil.format("{}.{}", StrUtil.uuid(), FileUtil.extName(file.getOriginalFilename()));
        try {
            Files.createDirectories(Paths.get(rootDir + pathDir));
            Path path = Paths.get(rootDir + pathDir + fileName);
            Files.write(path, file.getBytes());
        } catch (Exception e) {
            log.error("文件上传异常:{}", e.getLocalizedMessage());
            throw new BizException(StrUtil.format("文件上传异常:{}", file.getOriginalFilename()));
        }
        SystemFile systemFile = new SystemFile();
        systemFile.setName(file.getOriginalFilename())
                .setDir(rootDir)
                .setPath(pathDir + fileName)
                .setContentType(file.getContentType())
                .setExtName(FileUtil.extName(file.getOriginalFilename()))
                .setSize(file.getSize())
                .setTotalSize(file.getSize())
                .setDataStatus(CommonConstants.YES)
                .setContentType(MediaTypeFactory.getMediaType(file.getOriginalFilename()).orElse(MediaType.APPLICATION_OCTET_STREAM).toString());
        save(systemFile);
        return systemFile;
    }

    @Override
    public ResponseEntity<byte[]> downloadLocalFile(String fileName, String localFileDir) {
        return null;
    }

}
