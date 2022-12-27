package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 用户类型
 */
@Data
@ApiModel("userRole添加")
public class UserRoleEditReq {
    @ApiModelProperty("唯一")
    private Long id;
    @ApiModelProperty("1可用，2不可用")
    private Integer status;
    @ApiModelProperty("user_base.id")
    private Long userId;
    @ApiModelProperty("1求职者，2企业hr,3猎头...")
    private Integer userType;
}
