package com.geovis.manager.bs.constant;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.util.List;

/**
 * 成绩管理和证书发放_证书状态常量
 */
@Getter
public enum ExamResultCertificateStatusConstant {

    // 工种：1-特种作业 2-安全管理人员 3-危险化学品作业人员 4-剧毒化学品作业人员
    SOON_EXPIRE("1", "即将过期"),

    HAS_EXPIRE("2", "已过期"),

    NOT_EXPIRE("3", "已发证");

    private String code;

    private String text;

    ExamResultCertificateStatusConstant(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getTextByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return StrUtil.EMPTY;
        }
        for (ExamResultCertificateStatusConstant type : ExamResultCertificateStatusConstant.values()) {
            if (StrUtil.equals(type.code, code)) {
                return type.text;
            }
        }
        return StrUtil.EMPTY;
    }

}
