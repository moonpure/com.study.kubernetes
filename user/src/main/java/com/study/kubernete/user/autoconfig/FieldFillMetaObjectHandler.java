package com.study.kubernete.user.autoconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.UserBaseModel;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class FieldFillMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private Sequence sequence;
    @Autowired
    private RequestHeaderServiceImpl requestHeaderService;

    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "id", () -> sequence.nextId(), Long.class);
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);



        UserBaseModel userBase= requestHeaderService.getUser();
        if(userBase==null)
        {
            this.strictInsertFill(metaObject, "createId", () -> 0l, Long.class);
            this.strictInsertFill(metaObject, "updateId", () -> 0l, Long.class);
            return;
        }
        Long userId = userBase.getId();
        if (userId != null) {
            this.strictInsertFill(metaObject, "createId", () -> userId, Long.class);
            this.strictInsertFill(metaObject, "updateId", () -> userId, Long.class);
        }
        else
        {
            this.strictInsertFill(metaObject, "createId", () -> 0l, Long.class);
            this.strictInsertFill(metaObject, "updateId", () -> 0l, Long.class);
        }

    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);

        UserBaseModel userBase= requestHeaderService.getUser();
        if(userBase==null)
        {
            this.strictInsertFill(metaObject, "updateId", () -> 0l, Long.class);
            return;
        }
        Long userId = userBase.getId();
        if (userId != null) {
            this.strictUpdateFill(metaObject, "updateId", () -> userId, Long.class);
        }
        else
        {
            this.strictInsertFill(metaObject, "updateId", () -> 0l, Long.class);
        }

    }
}
