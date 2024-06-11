package com.geovis.manager.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geovis.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author zengds
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_file")
@ApiModel(value = "SystemFile对象", description = "附件表")
public class SystemFile extends BaseEntity {

    @ApiModelProperty(value = "文件名称")
    @Length(max = 255, message = "文件名称最长不能超过{255}个字符")
    private String name;

    @ApiModelProperty(value = "文件扩展名")
    @Length(max = 128, message = "文件扩展名最长不能超过{128}个字符")
    private String extName;

    @ApiModelProperty(value = "文件类型")
    @Length(max = 128, message = "文件类型最长不能超过{128}个字符")
    private String contentType;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "总文件大小")
    private Long totalSize;

    @ApiModelProperty(value = "存储目录")
    @Length(max = 255, message = "存储目录最长不能超过{255}个字符")
    private String dir;

    @ApiModelProperty(value = "存储路径")
    @Length(max = 512, message = "存储路径最长不能超过{512}个字符")
    private String path;

    @ApiModelProperty(value = "文件状态")
    @Length(max = 20, message = "文件状态最长不能超过{20}个字符")
    private String dataStatus;

    @ApiModelProperty(value = "文件md5值")
    @Length(max = 512, message = "文件类型最长不能超过{512}个字符")
    private String md5;

}
