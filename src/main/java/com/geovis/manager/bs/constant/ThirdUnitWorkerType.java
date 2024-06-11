package com.geovis.manager.bs.constant;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.util.List;

/**
 * 第三方单位工种常量
 */
@Getter
public enum ThirdUnitWorkerType {

    // 工种：1-特种作业 2-安全管理人员 3-危险化学品作业人员 4-剧毒化学品作业人员
    SPECIAL_WORK("1", "特种作业"),

    SAFE_MANAGER_WORK("2", "安全管理人员"),

    DANGER_CHEMICALS_WORK("3", "危险化学品作业人员"),

    HYPER_TOXIC_CHEMICALS_WORK("4", "剧毒化学品作业人员");

    private String code;

    private String text;

    ThirdUnitWorkerType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getTextByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return StrUtil.EMPTY;
        }
        for (ThirdUnitWorkerType type : ThirdUnitWorkerType.values()) {
            if (StrUtil.equals(type.code, code)) {
                return type.text;
            }
        }
        return StrUtil.EMPTY;
    }

    public static String getWorkerTypeText(String workType) {
        if (StrUtil.isNotEmpty(workType)) {
            String[] split = workType.split(StrUtil.COMMA);
            List<String> workTypeTextList = CollUtil.list(false);
            for (String workTypeCode : split) {
                String workTypeText = getTextByCode(workTypeCode);
                if (StrUtil.isNotEmpty(workTypeText)) {
                    workTypeTextList.add(workTypeText);
                }
            }
            return CollUtil.join(workTypeTextList, StrUtil.COMMA);
        }
        return StrUtil.EMPTY;
    }


}
