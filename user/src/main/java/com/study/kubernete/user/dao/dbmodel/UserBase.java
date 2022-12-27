package com.study.kubernete.user.dao.dbmodel;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
public class UserBase  {
    /**
     * 创建id
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateId;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
