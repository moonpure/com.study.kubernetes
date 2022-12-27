package com.study.kubernete.user.sensitive;

import lombok.Getter;

@Getter
public enum SensitiveTypeEnum {
    /**
     * 手机(四位打码)
     */
    MOBILE(3, 4),
    /**
     * 身份证(6位打码)
     */
    ID(6, 4),
    /**
     * 电子邮箱(5位打码)
     */
    EMAIL(3, 3),
    /**
     * 银行卡（显示前三位和后四位）
     */
    BANK_CARD(3, 4),
    /**
     * 密码（显示第一位和最后一位）
     */
    PASSWORD(2, 2),
    /**
     * 中文名(只显示第一位)
     */
    CHINESE_NAME(1, 1),
    /**
     * 座机号（4位）
     */
    FIXED_PHONE(2, 2),
    /**
     * 地址(5位）
     */
    ADDRESS(3, 3),
    /**
     * 自定义
     */
    CUSTOM(0, 0),
    /**
     * 不进行脱敏
     */
    NOSENSTIVE(0, 0);

    SensitiveTypeEnum(Integer startLen, Integer endLen) {
        this.startLen = startLen;
        this.endLen = endLen;
    }


    /**
     * 正数为开始保留的长度，负数为开始隐藏的长度
     */
    private Integer startLen;
    /**
     * 正数为最后保留长度，负数为前面保留数
     */
    private Integer endLen;
}
