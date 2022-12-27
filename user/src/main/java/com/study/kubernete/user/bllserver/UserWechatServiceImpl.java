package com.study.kubernete.user.bllserver;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserWechat;
import com.study.kubernete.user.dao.dbservice.DbUserWechatServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserWechatResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_wechat 微信用户
 */
@Service
public class UserWechatServiceImpl {
    @Resource
    private DbUserWechatServiceImpl dbUserWechatService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_wechat 添加
     */
    @Cacheable(cacheNames = "user-UserWechatSave", key = "#entity.id")
    public Result<Boolean> save(UserWechatAddReq entity) {
        UserWechat baseEntity = BeanCopierTool.convert(entity, UserWechat.class);
        boolean bool = dbUserWechatService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_wechat getId
     */
    @Cacheable(cacheNames = "user-UserWechatById", key = "#id", sync = true)
    public Result<UserWechatResp> getById(Long id) {
        UserWechat baseEntity = dbUserWechatService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        UserWechatResp resp = BeanCopierTool.convert(baseEntity, UserWechatResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(resp);
    }

    /**
     * user_wechat 逻辑删除
     */
    @CacheEvict(value = "user-UserWechatById", key = "#id")
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserWechatService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_wechat id修改
     */
    @CacheEvict(value = "user-UserWechatById", key = "#entity.id")
    public Result<Boolean> updateById(UserWechatEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserWechat baseEntity = BeanCopierTool.convert(entity, UserWechat.class);
        return Result.createBySuccess(dbUserWechatService.updateById(baseEntity));
    }

    /**
     * user_wechat 修改
     */
    public Result<Boolean> update(List<UserWechatEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserWechat> baseEntities = convertService.copyList(entities, UserWechat.class);
        boolean bool = dbUserWechatService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_wechat getOne
     */
    public Result<UserWechatResp> getOne(UserWechatQueryReq entity) {
        UserWechat baseEntity = BeanCopierTool.convert(entity, UserWechat.class);
        UserWechat oneEntity = dbUserWechatService.getOne(baseEntity);
        UserWechatResp result = BeanCopierTool.convert(oneEntity, UserWechatResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_wechat List查询
     */
    public Result<List<UserWechatResp>> selectList(UserWechatQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserWechat baseEntity=BeanCopierTool.convert(entity,UserWechat.class);
        List<UserWechat> selectPage = dbUserWechatService.selectList(baseEntity);
        List<UserWechatResp> results = convertService.copyList(selectPage, UserWechatResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_wechat 分页查询
     */
    public Result<List<UserWechatResp>> selectPage(QueryPage<UserWechatQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserWechat> queryPage=convertService.convertQueryPage(entity,UserWechat.class);
        List<UserWechat> selectPage = dbUserWechatService.selectPage(queryPage);
        List<UserWechatResp> results = convertService.copyList(selectPage, UserWechatResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wechat in 查询
     */
    public <T> Result<List<UserWechatResp>> selectIn(QueryIn<UserWechatQueryReq, T> entity) {
        QueryIn<UserWechat, T> queryIn = convertService.convertIn(entity, UserWechat.class);
        List<UserWechat> entitys = dbUserWechatService.selectIn(queryIn);
        List<UserWechatResp> results = convertService.copyList(entitys, UserWechatResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wechat Bettween查询
     */
    public Result<List<UserWechatResp>> selectBetween(QueryBetween<UserWechatQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserWechat> between = convertService.convertBetween(entity, UserWechat.class);

        List<UserWechat> selectPage = dbUserWechatService.selectBetween(between);
        List<UserWechatResp> results = convertService.copyList(selectPage, UserWechatResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wechat 组合查询
     */
    public <T> Result<List<UserWechatResp>> selectQuery(QueryFull<UserWechatQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserWechat, T> queryFull = convertService.convertQueryFull(entity, UserWechat.class);
        List<UserWechat> selectPage = dbUserWechatService.selectQuery(queryFull);
        List<UserWechatResp> results = convertService.copyList(selectPage, UserWechatResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wechat like查询
     * @param entity
     * @return
     */
    public  Result<List<UserWechatResp>> selectLike(QueryLike<UserWechatQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserWechat> pageEntity = convertService.convertLike(entity,UserWechat.class);
        pageEntity.setSelect("id,name");
       List<UserWechat> selectPage=dbUserWechatService.selectLike(pageEntity);
        List<UserWechatResp> results = convertService.copyList(selectPage, UserWechatResp.class);
        return Result.createBySuccess(results);
    }
}
