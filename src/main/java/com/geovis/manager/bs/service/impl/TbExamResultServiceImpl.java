package com.geovis.manager.bs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geovis.common.auth.util.AuthUtil;
import com.geovis.common.core.util.NumberUtil;
import com.geovis.common.mybatis.page.PageParam;
import com.geovis.common.mybatis.page.PageResult;
import com.geovis.manager.bs.constant.ExamResultCertificateStatusConstant;
import com.geovis.manager.bs.constant.ThirdUnitWorkerType;
import com.geovis.manager.bs.dto.TbExamResultDTO;
import com.geovis.manager.bs.dto.TbExamResultQueryDTO;
import com.geovis.manager.bs.entity.TbArticle;
import com.geovis.manager.bs.entity.TbArticleLearned;
import com.geovis.manager.bs.entity.TbExamResult;
import com.geovis.manager.bs.entity.TbThirdUnit;
import com.geovis.manager.bs.mapper.TbExamResultMapper;
import com.geovis.manager.bs.service.ITbArticleLearnedService;
import com.geovis.manager.bs.service.ITbArticleService;
import com.geovis.manager.bs.service.ITbExamResultService;
import com.geovis.manager.bs.service.ITbThirdUnitService;
import com.geovis.manager.system.entity.SystemUser;
import com.geovis.manager.system.service.ISystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 成绩管理和证书发放 服务实现类
 * </p>
 *
 * @author zengds
 * @since 2024-04-26
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TbExamResultServiceImpl extends ServiceImpl<TbExamResultMapper, TbExamResult> implements ITbExamResultService {

    private final ITbArticleService articleService;

    private final ITbArticleLearnedService articleLearnedService;

    private final ISystemUserService systemUserService;

    private final ITbThirdUnitService thirdUnitService;

    @Override
    public PageResult<TbExamResultDTO> getPage(PageParam<TbExamResultQueryDTO> pageParam) {
        IPage<TbExamResultDTO> page = pageParam.buildPage();
        baseMapper.selectDataList(page, getObjectQueryWrapper(pageParam.getQuery()));
        List<String> userIdList = CollUtil.list(false);
        List<String> workerTypeList = CollUtil.list(false);
        page.getRecords().forEach(item -> {
            userIdList.add(item.getUserId());
            workerTypeList.add(item.getWorkerType());
            // 工种转换
            item.setWorkerType(ThirdUnitWorkerType.getWorkerTypeText(item.getWorkerType()));
            // 证书状态转换
            item.setCertificateStatus(formatCertificateStatus(item.getCertificateExpireDate()));
        });
        Map<String, List<TbArticleLearned>> learnedListMap = IterUtil.toListMap(articleLearnedService.list(Wrappers.lambdaQuery(TbArticleLearned.class).in(CollUtil.isNotEmpty(userIdList),TbArticleLearned::getUserId, userIdList)), TbArticleLearned::getUserId);
        Map<String, List<TbArticle>> articleListMap = IterUtil.toListMap(articleService.list(Wrappers.lambdaQuery(TbArticle.class).in(CollUtil.isNotEmpty(workerTypeList),TbArticle::getWorkType, workerTypeList)), TbArticle::getWorkType);
        page.getRecords().forEach(item -> {
            List<TbArticle> tbArticles = articleListMap.get(item.getWorkerType());
            List<TbArticleLearned> tbArticleLearnedList = learnedListMap.get(item.getUserId());
            item.setStudyRate(new BigDecimal(0));
            if (CollUtil.isNotEmpty(tbArticles) && CollUtil.isNotEmpty(tbArticleLearnedList)) {
                // 学习进度
                item.setStudyRate(NumberUtil.div(new BigDecimal(tbArticleLearnedList.size()), new BigDecimal(tbArticles.size()), 2));
            }
        });
        return new PageResult<>(page);
    }

    @Override
    public TbExamResultDTO getLastExamResult() {
        TbExamResult examResult = getOne(Wrappers.lambdaQuery(TbExamResult.class).eq(TbExamResult::getUserId, AuthUtil.getCurrentUserId()));
        if (ObjectUtil.isNotEmpty(examResult)) {
            TbExamResultDTO dto = BeanUtil.copyProperties(examResult, TbExamResultDTO.class);
            SystemUser systemUser = systemUserService.getById(AuthUtil.getCurrentUserId());
            TbThirdUnit thirdUnit = thirdUnitService.getById(systemUser.getRelatedId());
            dto.setCertificateStatus(formatCertificateStatus(examResult.getCertificateExpireDate()))
                    .setUserRealName(systemUser.getRealName())
                    .setWorkerType(ThirdUnitWorkerType.getWorkerTypeText(systemUser.getWorkerType()))
                    .setThirdUnitName(thirdUnit.getName());
            return dto;
        }
        return null;
    }

    private String formatCertificateStatus(LocalDate expireDate) {
        String status = ExamResultCertificateStatusConstant.NOT_EXPIRE.getText();
        LocalDateTime offset = LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 7, ChronoUnit.DAYS);
        LocalDate day7After = LocalDate.of(offset.getYear(), offset.getMonth(), offset.getDayOfMonth());
        // 未过期
        if (expireDate.compareTo(LocalDate.now()) <= 0) {
            if (day7After.compareTo(LocalDate.now()) > 0) {
                // 即将到期
                status = ExamResultCertificateStatusConstant.SOON_EXPIRE.getText();
            }
        } else {
            // 已过期
            status = ExamResultCertificateStatusConstant.HAS_EXPIRE.getText();
        }
        return status;
    }

    @NotNull
    private QueryWrapper<Object> getObjectQueryWrapper(TbExamResultQueryDTO queryDTO) {
        QueryWrapper<Object> queryWrapper = Wrappers.query();
        queryWrapper.eq("1",1).orderByDesc("a.update_time");
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            queryWrapper.eq(ObjectUtil.isNotEmpty(queryDTO.getWorkerType()), "b.worker_type", queryDTO.getWorkerType());
            if (StrUtil.equals(ExamResultCertificateStatusConstant.SOON_EXPIRE.getCode(), queryDTO.getCertificateStatus())) {
                LocalDateTime offset = LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 7, ChronoUnit.DAYS);
                queryWrapper.gt("a.certificate_expire_date", LocalDate.now())
                        .le("a.certificate_expire_date", LocalDate.of(offset.getYear(), offset.getMonth(), offset.getDayOfMonth()));
            }
            if (StrUtil.equals(ExamResultCertificateStatusConstant.HAS_EXPIRE.getCode(), queryDTO.getCertificateStatus())) {
                queryWrapper.lt("a.certificate_expire_date", LocalDate.now());
            }
            if (StrUtil.equals(ExamResultCertificateStatusConstant.NOT_EXPIRE.getCode(), queryDTO.getCertificateStatus())) {
                queryWrapper.ge("a.certificate_expire_date", LocalDate.now());
            }
        }
        return queryWrapper;
    }
}
