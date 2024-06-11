package com.geovis.manager.bs.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import com.geovis.manager.bs.service.ITbParkGridPatrolTmpUserService;
import org.springframework.web.bind.annotation.RestController;
import com.geovis.common.core.controller.BaseController;

/**
 * <p>
 * 园区网格_巡查模版_成员表 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-22
*/
@RestController
@RequestMapping("/bs/tbParkGridPatrolTmpUser")
@RequiredArgsConstructor
@Api(value = "园区网格_巡查模版_成员表接口", tags = "园区网格_巡查模版_成员表接口")
@Slf4j
@Validated
public class TbParkGridPatrolTmpUserController extends BaseController<ITbParkGridPatrolTmpUserService> {

}
