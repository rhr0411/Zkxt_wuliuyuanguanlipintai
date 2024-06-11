package com.geovis.manager.bs.constant;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 消息类型枚举
 */
@Getter
public enum MessageTypeConstant {

    // 消息类型：1-隐患预警 2-开停车冲突 3-年度履诺报告到期 4-其他
    RISK_HAZARDS("1", "隐患预警", "您有一条新的隐患风险预警，请及时处理"),

    EQP("2", "开停车冲突", "您有一条新的装置开停车和大检修冲突预警，请及时处理"),

    COMMITMENT("3", "年度履诺报告到期", "贵司的年度履诺报告即将到期，请及时更新"),

    OTHER("4", "其他", "");

    private String code;

    private String text;

    private String msgTemplate;

    MessageTypeConstant(String code, String text, String msgTemplate) {
        this.code = code;
        this.text = text;
        this.msgTemplate = msgTemplate;
    }

    public static String getTextByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return StrUtil.EMPTY;
        }
        for (MessageTypeConstant type : MessageTypeConstant.values()) {
            if (StrUtil.equals(type.code, code)) {
                return type.text;
            }
        }
        return StrUtil.EMPTY;
    }

}
