package com.study.kubernete.user.edition;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    /**
     * 版本
     *
     * @return
     */
    String value() default "1.0.0";
}
