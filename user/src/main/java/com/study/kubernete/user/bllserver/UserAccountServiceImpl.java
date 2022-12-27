package com.study.kubernete.user.bllserver;


import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserAccount;
import com.study.kubernete.user.dao.dbservice.DbUserAccountServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserAccountResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_account 登录用户
 */
@Service
public class UserAccountServiceImpl {
    @Resource
    private DbUserAccountServiceImpl dbUserAccountService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_account 添加
     */
    @Cacheable(cacheNames = "user-UserAccountSave", key = "#entity.id")
    public Result<Boolean> save(UserAccountAddReq entity) {
        UserAccount baseEntity = BeanCopierTool.convert(entity, UserAccount.class);
        boolean bool = dbUserAccountService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_account getId
     */
    @Cacheable(cacheNames = "user-UserAccountById", key = "#id", sync = true)
    public Result<UserAccountResp> getById(Long id) {
        UserAccount baseEntity = dbUserAccountService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        UserAccountResp resp = BeanCopierTool.convert(baseEntity, UserAccountResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(resp);
    }

    /**
     * user_account 逻辑删除
     */
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserAccountService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_account id修改
     */
    @CacheEvict(value = "user-UserAccountById", key = "#entity.id")
    public Result<Boolean> updateById(UserAccountEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserAccount baseEntity = BeanCopierTool.convert(entity, UserAccount.class);
        return Result.createBySuccess(dbUserAccountService.updateById(baseEntity));
    }

    /**
     * user_account 修改
     */
    public Result<Boolean> update(List<UserAccountEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserAccount> baseEntities = convertService.copyList(entities, UserAccount.class);
        boolean bool = dbUserAccountService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_account getOne
     */
    public Result<UserAccountResp> getOne(UserAccountQueryReq entity) {
        UserAccount baseEntity = BeanCopierTool.convert(entity, UserAccount.class);
        UserAccount oneEntity = dbUserAccountService.getOne(baseEntity);
        UserAccountResp result = BeanCopierTool.convert(oneEntity, UserAccountResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_account List查询
     */
    public Result<List<UserAccountResp>> selectList(UserAccountQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserAccount baseEntity=BeanCopierTool.convert(entity,UserAccount.class);
        List<UserAccount> selectPage = dbUserAccountService.selectList(baseEntity);
        List<UserAccountResp> results = convertService.copyList(selectPage, UserAccountResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_account 分页查询
     */
    public Result<List<UserAccountResp>> selectPage(QueryPage<UserAccountQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserAccount> queryPage=convertService.convertQueryPage(entity,UserAccount.class);
        List<UserAccount> selectPage = dbUserAccountService.selectPage(queryPage);
        List<UserAccountResp> results = convertService.copyList(selectPage, UserAccountResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_account in 查询
     */
    public <T> Result<List<UserAccountResp>> selectIn(QueryIn<UserAccountQueryReq, T> entity) {
        QueryIn<UserAccount, T> queryIn = convertService.convertIn(entity, UserAccount.class);
        List<UserAccount> entitys = dbUserAccountService.selectIn(queryIn);
        List<UserAccountResp> results = convertService.copyList(entitys, UserAccountResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_account Bettween查询
     */
    public Result<List<UserAccountResp>> selectBetween(QueryBetween<UserAccountQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserAccount> between = convertService.convertBetween(entity, UserAccount.class);

        List<UserAccount> selectPage = dbUserAccountService.selectBetween(between);
        List<UserAccountResp> results = convertService.copyList(selectPage, UserAccountResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_account 组合查询
     */
    public <T> Result<List<UserAccountResp>> selectQuery(QueryFull<UserAccountQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserAccount, T> queryFull = convertService.convertQueryFull(entity, UserAccount.class);
        List<UserAccount> selectPage = dbUserAccountService.selectQuery(queryFull);
        List<UserAccountResp> results = convertService.copyList(selectPage, UserAccountResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_account like查询
     * @param entity
     * @return
     */
    public  Result<List<UserAccountResp>> selectLike(QueryLike<UserAccountQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserAccount> pageEntity = convertService.convertLike(entity,UserAccount.class);
        pageEntity.setSelect("id,name");
       List<UserAccount> selectPage=dbUserAccountService.selectLike(pageEntity);
        List<UserAccountResp> results = convertService.copyList(selectPage, UserAccountResp.class);
        return Result.createBySuccess(results);
    }
}
