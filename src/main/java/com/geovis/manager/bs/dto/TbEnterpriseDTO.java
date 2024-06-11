package com.geovis.manager.bs.dto;

import com.geovis.manager.bs.entity.TbEnterprise;
import com.geovis.manager.system.dto.SystemFileDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 企业详情
 *
 * @author zengds
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "TbEnterpriseDTO", description = "企业详情")
public class TbEnterpriseDTO extends TbEnterprise {

    @ApiModelProperty("附件列表")
    private List<SystemFileDTO> fileList;

}
