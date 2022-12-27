package com.study.kubernete.user.dao.dbmodel;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户类型
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Setter
@Getter
public class UserRole  {
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
     * 唯一
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 1可用，2不可用
     */
    private Integer status;
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
    /**
     * 1求职者，2企业hr,3猎头...
     */
    private Integer userType;
}
