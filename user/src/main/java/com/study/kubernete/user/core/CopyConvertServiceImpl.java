package com.study.kubernete.user.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.sensitive.SensitiveTool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CopyConvertServiceImpl {
    /**
     * 组装排序
     *
     * @param query
     * @return
     */
    public List<OrderItem> convertOrderParameter(QueryPage query) {
        List<OrderItem> orderItems = new ArrayList<>();
        if (query == null) {
            return orderItems;
        }
        List<String> ascStrs = query.getOrderAsc();
        if (ascStrs != null && ascStrs.size() > 0) {
            for (String str : ascStrs) {
                OrderItem item = OrderItem.asc(str);
                orderItems.add(item);
            }
        }

        List<String> descStrs = query.getOrderDesc();
        if (descStrs != null && descStrs.size() > 0) {
            for (String str : descStrs) {
                OrderItem item = OrderItem.desc(str);
                orderItems.add(item);
            }
        }
        return orderItems;
    }

    /**
     * 输入page转换MP ipage
     *
     * @param query
     * @param <T>
     * @return
     */
    public <T, S> Page<T> convertPage(QueryPage<S> query) {

        Page<T> pageEntity = new Page<>();
        pageEntity.setSearchCount(false);
        if (query == null) {
            return pageEntity;
        }
        pageEntity.setCurrent(query.getCurrent());
        pageEntity.setSize(query.getSize());
        pageEntity.setOrders(convertOrderParameter(query));
        return pageEntity;
    }

    /**
     * 组装 between 条件
     *
     * @param entity
     * @param query
     * @param <T>
     * @return
     */
    public <T, S> QueryWrapper<T> convertBetween(QueryBetween<S> entity, QueryWrapper<T> query) {
        if (entity == null) {
            return query;
        }
        List<BetweenBase<Long>> longList = entity.getBetweenLong();
        if (longList != null && longList.size() > 0) {
            for (BetweenBase<Long> beLong : longList) {
                if (beLong == null) {
                    continue;
                }
                if (beLong.getBeginValue() != null) {
                    query.ge(beLong.getColumnName(), beLong.getBeginValue());
                }
                if (beLong.getEndValue() != null) {
                    query.lt(beLong.getColumnName(), beLong.getEndValue());
                }
            }
        }
        List<BetweenBase<LocalDateTime>> dateList = entity.getBetweenDate();
        if (dateList != null && dateList.size() > 0) {
            for (BetweenBase<LocalDateTime> beDate : dateList) {
                if (beDate == null) {
                    continue;
                }
                if (beDate.getBeginValue() != null) {
                    query.ge(beDate.getColumnName(), beDate.getBeginValue());
                }
                if (beDate.getEndValue() != null) {
                    query.lt(beDate.getColumnName(), beDate.getEndValue());
                }
            }
        }
        return query;
    }

    /**
     * Result 转换成实体
     *
     * @param result
     * @param <T>
     * @return
     */
    public <T> T convertResult(Result<T> result) {
        if (result == null || !result.isSuccess()) {
            return null;
        }
        return result.getData();
    }

    /**
     * Result 实体list 转换
     *
     * @param result
     * @param <T>
     * @return
     */
    public <T> List<T> convertResultList(Result<List<T>> result) {
        if (result == null || !result.isSuccess()) {
            return null;
        }
        return result.getData();
    }

    /**
     * bean copy 类型转换(无脱敏)
     *
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public <S, T> Result<T> copyResult(Result<S> source, Class<T> target) {
        if (source == null) {
            return null;
        }
        if (!source.isSuccess()) {
            Result<T> tResult = new Result<>();
            tResult.setCode(source.getCode());
            tResult.setMessage(source.getMessage());
            return tResult;
        }
        T t = BeanCopierTool.convert(source.getData(), target);
        return Result.createBySuccess(t);
    }

    /**
     * bean copy 类型转换(list)
     *
     * @param sources
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyList(List<S> sources, Class<T> target) {
        if (sources == null || sources.isEmpty()) {
            return null;
        }
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = BeanCopierTool.convert(source, target);
            list.add(t);
        }
        return list;
    }

    /**
     * bean copy 类型转换(list,无脱敏)
     *
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public <S, T> Result<List<T>> copyResultList(Result<List<S>> source, Class<T> target) {
        if (source == null) {
            return null;
        }
        if (!source.isSuccess()) {
            Result<List<T>> tResult = new Result<>();
            tResult.setCode(source.getCode());
            tResult.setMessage(source.getMessage());
            return tResult;
        }
        List<T> tList = copyList(source.getData(), target);
        return Result.createBySuccess(tList);
    }

    /**
     * bean copy 类型转换(脱敏)
     *
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public <S, T> Result<T> copyResultSensitive(Result<S> source, Class<T> target) {
        Result<T> result = copyResult(source, target);
        if (result == null || !result.isSuccess()) {
            return result;
        }
        SensitiveTool.sensitive(result.getData());//脱敏
        return Result.createBySuccess(result.getData());
    }

    /**
     * bean copy 类型转换(List脱敏)
     *
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public <S, T> Result<List<T>> copyResultListSensitive(Result<List<S>> source, Class<T> target) {
        Result<List<T>> result = copyResultList(source, target);
        if (result == null || !result.isSuccess()) {
            return result;
        }
        for (T t : result.getData()) {
            SensitiveTool.sensitive(t);//脱敏
        }
        return Result.createBySuccess(result.getData());
    }

    public <S, T, O> QueryIn<O, T> convertIn(QueryIn<S, T> entity, Class<O> target) {
        QueryIn<O, T> queryIn = new QueryIn<>();
        queryIn.setInValues(entity.getInValues());
        queryIn.setColumnName(entity.getColumnName());
        O o = BeanCopierTool.convert(entity.getData(), target);
        queryIn.setData(o);
        return queryIn;
    }

    public <S, T> QueryBetween<T> convertBetween(QueryBetween<S> entity, Class<T> target) {
        QueryBetween<T> between = new QueryBetween<>();
        between.setBetweenDate(entity.getBetweenDate());
        between.setBetweenLong(entity.getBetweenLong());

        between.setCurrent(entity.getCurrent());
        between.setSize(entity.getSize());
        between.setOrderAsc(entity.getOrderAsc());
        between.setOrderDesc(entity.getOrderDesc());

        T t = BeanCopierTool.convert(entity.getData(), target);
        between.setData(t);
        return between;
    }

    public <S, T, O> QueryFull<O, T> convertQueryFull(QueryFull<S, T> entity, Class<O> target) {
        QueryFull<O, T> query = new QueryFull<>();
        query.setInValues(entity.getInValues());
        query.setColumnName(entity.getColumnName());
        query.setBetweenDate(entity.getBetweenDate());
        query.setBetweenLong(entity.getBetweenLong());

        query.setCurrent(entity.getCurrent());
        query.setSize(entity.getSize());
        query.setOrderAsc(entity.getOrderAsc());
        query.setOrderDesc(entity.getOrderDesc());

        O o = BeanCopierTool.convert(entity.getData(), target);
        query.setData(o);
        return query;
    }

    public <S, T> QueryPage<T> convertQueryPage(QueryPage<S> entity, Class<T> target) {
        QueryPage<T> query = new QueryPage<>();
        query.setCurrent(entity.getCurrent());
        query.setSize(entity.getSize());
        query.setOrderAsc(entity.getOrderAsc());
        query.setOrderDesc(entity.getOrderDesc());
        T t = BeanCopierTool.convert(entity.getData(), target);
        query.setData(t);
        return query;
    }

    public <S, T> QueryLike<T> convertLike(QueryLike<S> entity, Class<T> target) {
        QueryLike<T> query = new QueryLike<>();
        query.setColumnName(entity.getColumnName());
        query.setLikeValue(entity.getLikeValue());
        query.setCurrent(entity.getCurrent());
        query.setSize(entity.getSize());
        query.setOrderAsc(entity.getOrderAsc());
        query.setOrderDesc(entity.getOrderDesc());
        T t = BeanCopierTool.convert(entity.getData(), target);
        query.setData(t);
        return query;
    }

}
