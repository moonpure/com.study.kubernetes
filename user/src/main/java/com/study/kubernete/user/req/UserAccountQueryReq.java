package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 登录用户
 */
@Data
@ApiModel("userAccount添加")
public class UserAccountQueryReq {
    @ApiModelProperty("登录用户名，唯一")
    private String accountCode;
    @ApiModelProperty("user_base.id")
    private Long userId;
}
