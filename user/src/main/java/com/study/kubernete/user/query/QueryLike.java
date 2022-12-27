package com.study.kubernete.user.query;

import lombok.Data;
@Data
public class QueryLike<T> extends QueryPage<T> {
    /**
     * in查询的列名
     */
    private String columnName;
    /**
     *like值
     */
    private String likeValue;
}
