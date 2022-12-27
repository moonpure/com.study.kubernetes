package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 手机用户
 */
@Data
@ApiModel("userMobile添加")
public class UserMobileEditReq {
    @ApiModelProperty("手号，唯一")
    private String mobile;
    @ApiModelProperty("user_base.id")
    private Long userId;
}
