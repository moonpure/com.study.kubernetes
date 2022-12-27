package com.study.kubernete.user.bllserver;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserWallet;
import com.study.kubernete.user.dao.dbservice.DbUserWalletServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserWalletResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_wallet 用户钱包
 */
@Service
public class UserWalletServiceImpl {
    @Resource
    private DbUserWalletServiceImpl dbUserWalletService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_wallet 添加
     */
    @Cacheable(cacheNames = "user-UserWalletSave", key = "#entity.id")
    public Result<Boolean> save(UserWalletAddReq entity) {
        UserWallet baseEntity = BeanCopierTool.convert(entity, UserWallet.class);
        boolean bool = dbUserWalletService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_wallet getId
     */
    @Cacheable(cacheNames = "user-UserWalletById", key = "#id", sync = true)
    public Result<UserWalletResp> getById(Long id) {
        UserWallet baseEntity = dbUserWalletService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        UserWalletResp resp = BeanCopierTool.convert(baseEntity, UserWalletResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(resp);
    }

    /**
     * user_wallet 逻辑删除
     */
    @CacheEvict(value = "user-UserWalletById", key = "#id")
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserWalletService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_wallet id修改
     */
    @CacheEvict(value = "user-UserWalletById", key = "#entity.id")
    public Result<Boolean> updateById(UserWalletEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserWallet baseEntity = BeanCopierTool.convert(entity, UserWallet.class);
        return Result.createBySuccess(dbUserWalletService.updateById(baseEntity));
    }

    /**
     * user_wallet 修改
     */
    public Result<Boolean> update(List<UserWalletEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserWallet> baseEntities = convertService.copyList(entities, UserWallet.class);
        boolean bool = dbUserWalletService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_wallet getOne
     */
    public Result<UserWalletResp> getOne(UserWalletQueryReq entity) {
        UserWallet baseEntity = BeanCopierTool.convert(entity, UserWallet.class);
        UserWallet oneEntity = dbUserWalletService.getOne(baseEntity);
        UserWalletResp result = BeanCopierTool.convert(oneEntity, UserWalletResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_wallet List查询
     */
    public Result<List<UserWalletResp>> selectList(UserWalletQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserWallet baseEntity=BeanCopierTool.convert(entity,UserWallet.class);
        List<UserWallet> selectPage = dbUserWalletService.selectList(baseEntity);
        List<UserWalletResp> results = convertService.copyList(selectPage, UserWalletResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_wallet 分页查询
     */
    public Result<List<UserWalletResp>> selectPage(QueryPage<UserWalletQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserWallet> queryPage=convertService.convertQueryPage(entity,UserWallet.class);
        List<UserWallet> selectPage = dbUserWalletService.selectPage(queryPage);
        List<UserWalletResp> results = convertService.copyList(selectPage, UserWalletResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wallet in 查询
     */
    public <T> Result<List<UserWalletResp>> selectIn(QueryIn<UserWalletQueryReq, T> entity) {
        QueryIn<UserWallet, T> queryIn = convertService.convertIn(entity, UserWallet.class);
        List<UserWallet> entitys = dbUserWalletService.selectIn(queryIn);
        List<UserWalletResp> results = convertService.copyList(entitys, UserWalletResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wallet Bettween查询
     */
    public Result<List<UserWalletResp>> selectBetween(QueryBetween<UserWalletQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserWallet> between = convertService.convertBetween(entity, UserWallet.class);

        List<UserWallet> selectPage = dbUserWalletService.selectBetween(between);
        List<UserWalletResp> results = convertService.copyList(selectPage, UserWalletResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wallet 组合查询
     */
    public <T> Result<List<UserWalletResp>> selectQuery(QueryFull<UserWalletQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserWallet, T> queryFull = convertService.convertQueryFull(entity, UserWallet.class);
        List<UserWallet> selectPage = dbUserWalletService.selectQuery(queryFull);
        List<UserWalletResp> results = convertService.copyList(selectPage, UserWalletResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_wallet like查询
     * @param entity
     * @return
     */
    public  Result<List<UserWalletResp>> selectLike(QueryLike<UserWalletQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserWallet> pageEntity = convertService.convertLike(entity,UserWallet.class);
        pageEntity.setSelect("id,name");
       List<UserWallet> selectPage=dbUserWalletService.selectLike(pageEntity);
        List<UserWalletResp> results = convertService.copyList(selectPage, UserWalletResp.class);
        return Result.createBySuccess(results);
    }
}
