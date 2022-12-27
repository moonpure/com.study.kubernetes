package com.study.kubernete.user.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QueryFull<T, V> extends QueryBetween<T> {
    /**
     * in查询的列名
     */
    private String columnName;
    /**
     * in 值列表
     */
    private List<V> inValues;
}