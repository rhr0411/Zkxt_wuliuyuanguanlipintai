package com.geovis.manager.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 * @author 曾德实
 * @since 2020-09-01
 */
public interface ISystemFileService extends IService<SystemFile> {

    List<SystemFileDTO> getList(SystemFileQueryDTO dto);

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    SystemFile upload(MultipartFile file);

    /**
     * 下载本地文件
     *
     * @param fileName
     * @param localFileDir
     * @return
     */
    ResponseEntity<byte[]> downloadLocalFile(String fileName, String localFileDir);

}
