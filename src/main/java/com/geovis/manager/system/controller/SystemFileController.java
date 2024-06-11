package com.geovis.manager.system.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.core.exception.BizException;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.system.dto.SystemFileDTO;
import com.geovis.manager.system.dto.SystemFileQueryDTO;
import com.geovis.manager.system.entity.SystemFile;
import com.geovis.manager.system.service.ISystemFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author zengds
 */
@RestController
@RequestMapping("/system/systemFile")
@AllArgsConstructor
@Api(value = "系统管理模块-附件信息相关接口", tags = "系统管理模块-附件信息相关接口")
@Slf4j
@Validated
public class SystemFileController extends BaseController<ISystemFileService> {

    @ApiOperation(value = "附件上传", notes = "附件上传")
    @PostMapping({"/upload"})
    public Result<SystemFile> upload(MultipartFile file) {
        return Result.ok(baseService.upload(file));
    }

    @ApiOperation(value = "查询附件分页数据", notes = "查询附件分页数据")
    @PostMapping("/listPage")
    public Result<PageResult<SystemFile>> listPage(@RequestBody @Validated PageParam<SystemFile> pageParam) {
        IPage<SystemFile> page = pageParam.buildPage();
        SystemFile queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<SystemFile> wrapper = getQueryWrapper(queryDTO);
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<SystemFile>(page));
    }

    private LambdaQueryWrapper<SystemFile> getQueryWrapper(SystemFile queryDTO) {
        LambdaQueryWrapper<SystemFile> wrapper = Wrappers.lambdaQuery(SystemFile.class).orderByDesc(SystemFile::getCreateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getExtName()), SystemFile::getExtName, queryDTO.getExtName());
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getName()), SystemFile::getName, queryDTO.getName());
            wrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getContentType()), SystemFile::getContentType, queryDTO.getContentType());
        }
        return wrapper;
    }

    @ApiOperation(value = "查询附件列表", notes = "查询附件列表")
    @PostMapping("/list")
    public Result<List<SystemFileDTO>> list(@RequestBody @Validated SystemFileQueryDTO queryDTO) {
        return Result.ok(baseService.getList(queryDTO));
    }

    @ApiOperation(value = "查询所有附件数据", notes = "查询所有附件数据")
    @PostMapping("/getList")
    public Result<List<SystemFile>> getList(@RequestBody @Validated SystemFile systemFile) {
        return Result.ok(baseService.list(getQueryWrapper(systemFile)));
    }

    @ApiOperation(value = "批量删除附件数据", notes = "批量删除附件数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "idList", value = "删除id的list", required = true, dataType = "java.util.Set", paramType = "body")})
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@NotNull(message = "删除的id集合不能为空") @RequestBody(required = false) Set<String> idList) {
        baseService.removeBatchByIds(idList);
        return Result.ok();
    }

    @ApiOperation(value = "下载项目本地模板附件", notes = "通过文件名称下载项目本地模板附件")
    @GetMapping(value = "/downloadLocalFile")
    public ResponseEntity<byte[]> downloadLocalFile(@NotNull(message = "文件名不能为空") String fileName, String localFileDir) {
        return baseService.downloadLocalFile(fileName, localFileDir);
    }

    @ApiOperation(value = "查询附件下载", notes = "查询附件下载")
    @GetMapping(value = "/download/{id}")
    public void download(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
        SystemFile systemFile = baseService.getById(id);
        if (ObjectUtil.isEmpty(systemFile)) {
            throw new BizException("文件找不到");
        }
        response.setContentType(systemFile.getContentType());
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(systemFile.getName(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
        FileUtil.writeToStream(systemFile.getDir() + systemFile.getPath(), response.getOutputStream());
    }

}
