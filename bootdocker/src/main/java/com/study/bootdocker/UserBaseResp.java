package com.study.bootdocker;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户
 */
@Getter
@Setter
public class UserBaseResp {
    private String headimgurl;

    private Long id;

    private String mobile;

    private String name;

    private String nickName;

    private String password;
}
