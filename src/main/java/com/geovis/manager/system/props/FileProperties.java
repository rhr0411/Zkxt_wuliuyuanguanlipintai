package com.geovis.manager.system.props;

import cn.hutool.system.SystemUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 文件属性配置
 * </p>
 *
 * @author zengds
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "geovis.file")
@Configuration
public class FileProperties {

    /**
     * 文件上传根路径
     */
    private String uploadDir = SystemUtil.get(SystemUtil.USER_DIR);

    /**
     * 路径前缀
     */
    private String prefixDir = "resource";

    /**
     * 静态模版目录
     */
    private String templateDir;
}
