package com.geovis.manager.bs.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 考试配置
 * </p>
 *
 * @author 曾德实
 * @since 2020-07-14
 */
@Data
@ConfigurationProperties(prefix = "geovis.exam")
@Configuration
public class ExamProperties {

    /**
     * 每道题多少分，默认5分
     */
    private Integer questionScore = 5;

    /**
     * 合格分数，默认80分
     */
    private Integer qualifiedScore = 80;

    /**
     * 满分分数，默认100分
     */
    private Integer fullScore = 100;

    /**
     * 证书持续时间，默认3个月
     */
    private Integer certificateMonth = 3;

}
