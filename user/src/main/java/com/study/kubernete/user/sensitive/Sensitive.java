package com.study.kubernete.user.sensitive;

import java.lang.annotation.*;

/**
 * 脱敏注解(只用于字符串类型字段脱敏)
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Sensitive {
    SensitiveTypeEnum type();
    /**
     * 需要脱敏的正则表达式
     * @return
     */
    String regex() default ("");

    /**
     * 正则分组后脱敏组的索引
     * @return
     */
    int index() default (1);

    /**
     * 代替的符号，默认为 *
     * @return
     */
    char replacedCharacter() default ('*');
}
