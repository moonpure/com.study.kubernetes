package com.study.kubernete.user.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryIn<T,V> extends QueryBase<T> {
    /**
     * in查询的列名
     */
    private String columnName;
    /**
     * in 值列表
     */
    private List<V> inValues;
}
