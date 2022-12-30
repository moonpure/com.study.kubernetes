package com.study.bootdocker;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Setter
@Getter
public class UserBase {
    /**
     * 创建id
     */

    private Integer createId;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 
     */
    private Long id;
    /**
     * 手机号，只用于显示
     */
    private String mobile;
    /**
     * 用户显示名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 修改id
     */
    private Integer updateId;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
