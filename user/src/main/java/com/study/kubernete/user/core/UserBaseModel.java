package com.study.kubernete.user.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBaseModel {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户类型,1用户,2Hr
     */
    private Long userType;
    /**
    *用户角色Id
     */
    private Long roleId;
    /**
     * 公司Id,hr的公司，为0走默认公司
     */
    private Long  companyId;
    /**
     * 公司名
     */
    private String  companyName;
    /**
     * 用户在公司分配置的id
     */
    private Long companyUserId;

    @Override
    public String toString() {
        return id +
                "|" + name +
                "|" + nickName  +
                "|" + userType +
                "|" + roleId +
                "|" + companyId +
                "|" + companyName  +
                "|" + companyUserId ;
    }
}
