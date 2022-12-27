package com.study.kubernete.user.dao.dbmodel;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录用户
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Setter
@Getter
public class UserAccount  {
    /**
     * 登录用户名，唯一
     */
    @TableId(value = "account_code", type = IdType.INPUT)
    private String accountCode;
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
     * 1删除
     */
    @TableLogic
    private Integer deleted;
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
