package com.geovis.manager.system.dto;

import com.geovis.manager.system.entity.SystemFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <p>
 *附件查询
 * </p>
 *
 * @author zengds
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SystemFileQueryDTO", description = "系统模块DTO")
public class SystemFileQueryDTO extends SystemFile {

    @ApiModelProperty(value = "文件Id")
    private String fileId;

    @ApiModelProperty(value = "附加业务关联1(业务类型)")
    private String param1;

    @ApiModelProperty(value = "附加业务关联2(业务id1)")
    private String param2;

    @ApiModelProperty(value = "附加业务关联3(业务id2)")
    private String param3;

    @ApiModelProperty(value = "附加业务关联4 (业务id3)")
    private String param4;

    @ApiModelProperty(value = "附加业务关联4 (业务id3)")
    private String param5;

}
