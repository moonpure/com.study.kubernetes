package com.study.kubernete.user.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class QueryBetween<T> extends QueryPage<T> {
    List<BetweenBase<Long>> betweenLong;
    List<BetweenBase<LocalDateTime>> betweenDate;
}