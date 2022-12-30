package com.study.kubernete.user.bllserver;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserBase;
import com.study.kubernete.user.dao.dbservice.DbUserBaseServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserBaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_base 用户
 */
@Service
public class UserBaseServiceImpl {
    @Resource
    private DbUserBaseServiceImpl dbUserBaseService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_base 添加
     */
    @Cacheable(cacheNames = "user-UserBaseSave", key = "#entity.id")
    public Result<Boolean> save(UserBaseAddReq entity) {
        UserBase baseEntity = BeanCopierTool.convert(entity, UserBase.class);
        boolean bool = dbUserBaseService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_base getId
     */
    @Cacheable(cacheNames = "user-UserBaseById", key = "#id", sync = true)
    public Result<UserBase> getById(Long id) {
        UserBase baseEntity = dbUserBaseService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        BeanCopier beanCopier = BeanCopier.create(UserBase.class, UserBaseResp.class, false);
        UserBaseResp ur=new UserBaseResp();
        beanCopier.copy(baseEntity, ur, null);   //UserBaseResp resp = BeanCopierTool.convert(baseEntity, UserBaseResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(baseEntity);
    }

    /**
     * user_base 逻辑删除
     */
    @CacheEvict(value = "user-UserBaseById", key = "#id")
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserBaseService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_base id修改
     */
    @CacheEvict(value = "user-UserBaseById", key = "#entity.id")
    public Result<Boolean> updateById(UserBaseEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserBase baseEntity = BeanCopierTool.convert(entity, UserBase.class);
        return Result.createBySuccess(dbUserBaseService.updateById(baseEntity));
    }

    /**
     * user_base 修改
     */
    public Result<Boolean> update(List<UserBaseEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserBase> baseEntities = convertService.copyList(entities, UserBase.class);
        boolean bool = dbUserBaseService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_base getOne
     */
    public Result<UserBaseResp> getOne(UserBaseQueryReq entity) {
        UserBase baseEntity = BeanCopierTool.convert(entity, UserBase.class);
        UserBase oneEntity = dbUserBaseService.getOne(baseEntity);
        UserBaseResp result = BeanCopierTool.convert(oneEntity, UserBaseResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_base List查询
     */
    public Result<List<UserBaseResp>> selectList(UserBaseQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserBase baseEntity=BeanCopierTool.convert(entity,UserBase.class);
        List<UserBase> selectPage = dbUserBaseService.selectList(baseEntity);
        List<UserBaseResp> results = convertService.copyList(selectPage, UserBaseResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_base 分页查询
     */
    public Result<List<UserBaseResp>> selectPage(QueryPage<UserBaseQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserBase> queryPage=convertService.convertQueryPage(entity,UserBase.class);
        List<UserBase> selectPage = dbUserBaseService.selectPage(queryPage);
        List<UserBaseResp> results = convertService.copyList(selectPage, UserBaseResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_base in 查询
     */
    public <T> Result<List<UserBaseResp>> selectIn(QueryIn<UserBaseQueryReq, T> entity) {
        QueryIn<UserBase, T> queryIn = convertService.convertIn(entity, UserBase.class);
        List<UserBase> entitys = dbUserBaseService.selectIn(queryIn);
        List<UserBaseResp> results = convertService.copyList(entitys, UserBaseResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_base Bettween查询
     */
    public Result<List<UserBaseResp>> selectBetween(QueryBetween<UserBaseQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserBase> between = convertService.convertBetween(entity, UserBase.class);

        List<UserBase> selectPage = dbUserBaseService.selectBetween(between);
        List<UserBaseResp> results = convertService.copyList(selectPage, UserBaseResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_base 组合查询
     */
    public <T> Result<List<UserBaseResp>> selectQuery(QueryFull<UserBaseQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserBase, T> queryFull = convertService.convertQueryFull(entity, UserBase.class);
        List<UserBase> selectPage = dbUserBaseService.selectQuery(queryFull);
        List<UserBaseResp> results = convertService.copyList(selectPage, UserBaseResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_base like查询
     * @param entity
     * @return
     */
    public  Result<List<UserBaseResp>> selectLike(QueryLike<UserBaseQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserBase> pageEntity = convertService.convertLike(entity,UserBase.class);
        pageEntity.setSelect("id,name");
       List<UserBase> selectPage=dbUserBaseService.selectLike(pageEntity);
        List<UserBaseResp> results = convertService.copyList(selectPage, UserBaseResp.class);
        return Result.createBySuccess(results);
    }
}
