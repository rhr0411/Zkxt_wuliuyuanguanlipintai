package com.geovis.manager.system.dto;

import com.geovis.manager.system.entity.SystemFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 附件查询DTO
 * </p>
 *
 * @author 曾德实
 * @since 2022-05-01
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SystemFileDTO", description = "附件查询DTO")
public class SystemFileDTO extends SystemFile implements Serializable {

    @ApiModelProperty(value = "附加业务关联1(业务类型)")
    private String param1;

    @ApiModelProperty(value = "附加业务关联2(业务id1)")
    private String param2;

    @ApiModelProperty(value = "附加业务关联3(业务id2)")
    private String param3;

    @ApiModelProperty(value = "附加业务关联4 (业务id3)")
    private String param4;

    @ApiModelProperty(value = "附加业务关联5")
    private String param5;
}

