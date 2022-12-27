package com.study.kubernete.user.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QueryBase<T> {
    private T data;
    private String select;
}

