package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 微信用户
 */
@Data
@ApiModel("userWechat添加")
public class UserWechatAddReq {
    @ApiModelProperty("是否帮定手机，1为绑定。")
    private Long bindMobile;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("微信头像")
    private String headimgurl;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("微信openid")
    private String openId;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("1男性,2女性,0未知")
    private Integer sex;
    @ApiModelProperty("微信unionid")
    private String unionId;
    @ApiModelProperty("user_base.id")
    private Long userId;
}
