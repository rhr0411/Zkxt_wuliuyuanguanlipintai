package com.geovis.manager.bs.constant;

/**
 * 隐患常量
 *
 * @author zengds
 */
public interface RiskHazardsConstant {

    /**
     * 附件表示-隐患上报图片
     */
    String FILE_FLAG_RISK_HAZARDS_REPORTER = "RISK_HAZARDS_REPORTER";

    /**
     * 附件标识-处理附件
     */
    String FILE_FLAG_RISK_HAZARDS_HANDLE_FILE = "RISK_HAZARDS_HANDLE_FILE";

    /**
     * 附件标识-处理图片
     */
    String FILE_FLAG_RISK_HAZARDS_HANDLE_IMAGE = "RISK_HAZARDS_HANDLE_IMAGE";

    /**
     * 附件标识-复查附件
     */
    String FILE_FLAG_RISK_HAZARDS_CHECK_FILE = "RISK_HAZARDS_CHECK_FILE";

    /**
     * 附件标识-复查图片
     */
    String FILE_FLAG_RISK_HAZARDS_CHECK_IMAGE = "RISK_HAZARDS_CHECK_IMAGE";

    // 1-待复查 2-待整改 3-逾期未整改 4-已完成
    /**
     * 督办状态-待复查
     */
    String SUPERVISION_STATUS_BE_CHECK = "1";

    /**
     * 督办状态-待整改
     */
    String SUPERVISION_STATUS_BE_DOING = "2";

    /**
     * 督办状态-逾期未整改
     */
    String SUPERVISION_STATUS_OVER_NOT_HANDLE = "3";

    /**
     * 督办状态-已完成
     */
    String SUPERVISION_STATUS_COMPLETE = "4";

    // 隐患来源:1-日常检查 2-专项检查 3-重要节假日活动 4-其他 5-隐患上报
    /**
     * 隐患来源-日常检查
     */
    String DATA_SOURCE_DAILY = "1";
    /**
     * 隐患来源-专项检查
     */
    String DATA_SOURCE_SPECIAL = "2";
    /**
     * 隐患来源-重要节假日活动
     */
    String DATA_SOURCE_MAIN_ACTIVE = "3";
    /**
     * 隐患来源-其他
     */
    String DATA_SOURCE_OTHER = "4";
    /**
     * 隐患来源-随手拍
     */
    String DATA_SOURCE_RANDOM_CLAPPING = "5";
}
