package com.geovis.manager.system.controller;


import com.geovis.common.core.controller.BaseController;
import com.geovis.manager.system.service.ISystemModuleResourcesService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统模块资源中间表 前端控制器
 * </p>
 *
 * @author 王响
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/system/systemModuleResources")
@RequiredArgsConstructor
@Api(value = "系统管理模块-模块资源中间表", tags = "系统管理模块-模块资源中间表")
@Slf4j
@Validated
public class SystemModuleResourcesController extends BaseController<ISystemModuleResourcesService> {

}
