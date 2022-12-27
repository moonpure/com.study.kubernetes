package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 邮箱用户
 */
@Data
@ApiModel("userMail添加")
public class UserMailEditReq {
    @ApiModelProperty("邮箱，唯一")
    private String eMail;
    @ApiModelProperty("user_base.id")
    private Long userId;
}
