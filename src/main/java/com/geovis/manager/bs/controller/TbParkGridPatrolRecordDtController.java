package com.geovis.manager.bs.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import com.geovis.manager.bs.service.ITbParkGridPatrolRecordDtService;
import org.springframework.web.bind.annotation.RestController;
import com.geovis.common.core.controller.BaseController;

/**
 * <p>
 * 园区网格_巡检记录_明细信息 前端控制器
 * </p>
*
* @author zengds
* @since 2024-04-22
*/
@RestController
@RequestMapping("/bs/tbParkGridPatrolRecordDt")
@RequiredArgsConstructor
@Api(value = "园区网格_巡检记录_明细信息接口", tags = "园区网格_巡检记录_明细信息接口")
@Slf4j
@Validated
public class TbParkGridPatrolRecordDtController extends BaseController<ITbParkGridPatrolRecordDtService> {

}
