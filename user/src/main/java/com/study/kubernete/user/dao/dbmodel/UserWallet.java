package com.study.kubernete.user.dao.dbmodel;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户钱包
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Setter
@Getter
public class UserWallet  {
    /**
     * 创建时间
     */
    private LocalDateTime creatTime;
    /**
     * 创建id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;
    /**
     * 
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 身份 1.个人 2.公司
     */
    private Integer identity;
    /**
     * 余额
     */
    private Long remainingBalance;
    /**
     * 修改id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateId;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 用户id
     */
    private Long userId;
}
