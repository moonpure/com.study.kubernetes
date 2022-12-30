package com.study.kubernete.user.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class QueryPage<T> extends QueryBase<T> {
    private Long current=1L;
    private Long size=10L;
    private List<String> orderAsc;
    private List<String> orderDesc;
}
