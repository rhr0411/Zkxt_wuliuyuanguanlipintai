package com.geovis.manager.bs.controller;


import com.geovis.common.core.api.Result;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.dto.TbExamQuestionDTO;
import com.geovis.manager.bs.dto.TbExamQuestionQueryDTO;
import com.geovis.manager.bs.dto.TbExamQuestionSaveOrUpdateDTO;
import com.geovis.manager.bs.service.ITbExamQuestionService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 考试题库 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */
@RestController
@RequestMapping("/bs/tbExamQuestion")
@RequiredArgsConstructor
@Api(value = "考试题库接口", tags = "考试题库接口")
@Slf4j
@Validated
public class TbExamQuestionController extends BaseController<ITbExamQuestionService> {

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbExamQuestionDTO>> getPage(@RequestBody @Validated PageParam<TbExamQuestionQueryDTO> pageParam) {
        return Result.ok(baseService.getPage(pageParam));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody @Validated TbExamQuestionSaveOrUpdateDTO dto) {
        baseService.saveOrUpdateEntity(dto);
        return Result.ok();
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 4)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        return Result.ok();
    }

}
