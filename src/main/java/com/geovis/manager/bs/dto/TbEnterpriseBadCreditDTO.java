package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbEnterpriseBadCredit;
import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 企业不良信用管理对象
 * </p>
 *
 * @author zengds
 * @since 2024-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseBadCreditDTO", description = "企业不良信用管理对象")
public class TbEnterpriseBadCreditDTO extends TbEnterpriseBadCredit {

    @ApiModelProperty("企业ID")
    private List<SystemFileDTO> fileList;

}
