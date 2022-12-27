package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 用户钱包
 */
@Data
@ApiModel("userWallet添加")
public class UserWalletAddReq {
    @ApiModelProperty("创建时间")
    private LocalDateTime creatTime;
    @ApiModelProperty("")
    private Long id;
    @ApiModelProperty("身份 1.个人 2.公司")
    private Integer identity;
    @ApiModelProperty("余额")
    private Long remainingBalance;
    @ApiModelProperty("用户id")
    private Long userId;
}
