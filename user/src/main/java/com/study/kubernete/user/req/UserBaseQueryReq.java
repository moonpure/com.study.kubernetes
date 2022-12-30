package com.study.kubernete.user.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.*;
import lombok.Data;

/**
 * 用户
 */
@Data
@ApiModel("userBase添加")
public class UserBaseQueryReq {
    @ApiModelProperty("用户头像")
    private String headimgurl;
    @ApiModelProperty("")
    private Long id;
    @ApiModelProperty("手机号，只用于显示")
    private String mobile;
    @ApiModelProperty("用户显示名")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("用户密码")
    private String password;
}
