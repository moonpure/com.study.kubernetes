package com.study.kubernete.user.dao.dbmodel;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 微信用户
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Setter
@Getter
public class UserWechat  {
    /**
     * 是否帮定手机，1为绑定。
     */
    private Long bindMobile;
    /**
     * 城市
     */
    private String city;
    /**
     * 创建id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 微信头像
     */
    private String headimgurl;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 微信openid
     */
    @TableId(value = "open_id", type = IdType.INPUT)
    private String openId;
    /**
     * 省
     */
    private String province;
    /**
     * 1男性,2女性,0未知
     */
    private Integer sex;
    /**
     * 微信unionid
     */
    private String unionId;
    /**
     * 创建id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateId;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * user_base.id
     */
    private Long userId;
}
