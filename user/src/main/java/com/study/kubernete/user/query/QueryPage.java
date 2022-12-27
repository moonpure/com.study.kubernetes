package com.study.kubernete.user.query;

import lombok.Data;

import java.util.List;


@Data
public class QueryPage<T> extends QueryBase<T> {
    private Long current=1l;
    private Long size=10L;
    private List<String> orderAsc;
    private List<String> orderDesc;
}
