package com.geovis.manager.bs.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.api.Result;
import com.geovis.common.core.constant.CommonConstants;
import com.geovis.common.core.controller.BaseController;
import com.geovis.common.core.util.NumberUtil;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.ArticleConstant;
import com.geovis.manager.bs.dto.TbArticleSaveOrUpdateDTO;
import com.geovis.manager.bs.dto.TbArticleStudyStatisticDTO;
import com.geovis.manager.bs.dto.TbArticleWorkDTO;
import com.geovis.manager.bs.entity.TbArticle;
import com.geovis.manager.bs.entity.TbArticleLearned;
import com.geovis.manager.bs.service.ITbArticleLearnedService;
import com.geovis.manager.bs.service.ITbArticleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章管理 前端控制器
 * </p>
 *
 * @author zengds
 * @since 2024-04-22
 */
@RestController
@RequestMapping("/bs/tbArticle")
@RequiredArgsConstructor
@Api(value = "文章管理接口", tags = "文章管理接口")
@Slf4j
@Validated
public class TbArticleController extends BaseController<ITbArticleService> {

    private final ITbArticleLearnedService articleLearnedService;

    @ApiOperation("分页查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/getPage")
    public Result<PageResult<TbArticle>> getPage(@RequestBody @Validated PageParam<TbArticle> pageParam) {
        IPage<TbArticle> page = pageParam.buildPage();
        TbArticle queryDTO = pageParam.getQuery();
        LambdaQueryWrapper<TbArticle> wrapper = Wrappers.lambdaQuery(TbArticle.class).orderByDesc(TbArticle::getUpdateTime);
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            wrapper.like(ObjectUtil.isNotEmpty(queryDTO.getArticleName()), TbArticle::getArticleName, queryDTO.getArticleName())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getArticleType()), TbArticle::getArticleType, queryDTO.getArticleType())
                    .eq(ObjectUtil.isNotEmpty(queryDTO.getWorkType()), TbArticle::getWorkType, queryDTO.getWorkType());
        }
        baseService.page(page, wrapper);
        return Result.ok(new PageResult<TbArticle>(page));
    }

    @ApiOperation("保存或更新")
    @ApiOperationSupport(order = 2)
    @PostMapping("/saveOrUpdate")
    public Result<TbArticle> saveOrUpdate(@RequestBody @Validated TbArticleSaveOrUpdateDTO dto) {
        TbArticle entity = BeanUtil.copyProperties(dto, TbArticle.class);
        if (ObjectUtil.isEmpty(entity.getId())) {
            entity.setUploadTime(LocalDateTime.now());
        }
        baseService.saveOrUpdate(entity);
        return Result.ok(entity);
    }

    @ApiOperation("根据ID删除")
    @ApiOperationSupport(order = 3)
    @PostMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") String id) {
        baseService.removeById(id);
        // 删除学习进度
        articleLearnedService.remove(Wrappers.lambdaUpdate(TbArticleLearned.class).eq(TbArticleLearned::getArticleId,id));
        return Result.ok();
    }

    @ApiOperation("移动端-查询宣传栏列表")
    @ApiOperationSupport(order = 4)
    @PostMapping("/getPropagandaList")
    public Result<List<TbArticle>> getPropagandaList() {
        return Result.ok(baseService.list(Wrappers.lambdaQuery(TbArticle.class)
                .eq(TbArticle::getArticleType, ArticleConstant.ARTICLE_TYPE_PROPAGANDA)
                .orderByDesc(TbArticle::getUpdateTime)));
    }

    @ApiOperation("移动端-查询安全教育列表")
    @ApiOperationSupport(order = 5)
    @PostMapping("/getMyExamList")
    public Result<List<TbArticleWorkDTO>> getMyExamList() {
        return Result.ok(baseService.getMyExamList());
    }

    @ApiOperation("移动端-完成学习")
    @ApiOperationSupport(order = 6)
    @GetMapping("/completeStudy/{articleId}")
    public Result<?> completeStudy(@PathVariable("articleId") String articleId) {
        long count = articleLearnedService.count(Wrappers.lambdaQuery(TbArticleLearned.class).eq(TbArticleLearned::getArticleId, articleId).eq(TbArticleLearned::getUserId, AuthUtil.getCurrentUserId()));
        if (count == 0) {
            TbArticleLearned learned = new TbArticleLearned();
            learned.setArticleId(articleId)
                    .setUserId(AuthUtil.getCurrentUserId());
            articleLearnedService.save(learned);
        }
        return Result.ok();
    }

    @ApiOperation("移动端-安全教育学习进度")
    @ApiOperationSupport(order = 7)
    @GetMapping("/getMyStudyProgress")
    public Result<TbArticleStudyStatisticDTO> getMyStudyProgress() {
        TbArticleStudyStatisticDTO dto = new TbArticleStudyStatisticDTO();
        long count = articleLearnedService.count(Wrappers.lambdaQuery(TbArticleLearned.class).eq(TbArticleLearned::getUserId, AuthUtil.getCurrentUserId()));
        List<TbArticleWorkDTO> myExamList = baseService.getMyExamList();
        BigDecimal rate = NumberUtil.div(new BigDecimal(count), new BigDecimal(myExamList.size()), 2);
        dto.setCompleteRate(rate)
                .setHasQualify((new BigDecimal(0.8).compareTo(rate)) > 0 ? CommonConstants.NO : CommonConstants.YES);
        return Result.ok();
    }

}
