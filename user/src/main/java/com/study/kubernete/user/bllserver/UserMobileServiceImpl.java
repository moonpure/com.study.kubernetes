package com.study.kubernete.user.bllserver;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserMobile;
import com.study.kubernete.user.dao.dbservice.DbUserMobileServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserMobileResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_mobile 手机用户
 */
@Service
public class UserMobileServiceImpl {
    @Resource
    private DbUserMobileServiceImpl dbUserMobileService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_mobile 添加
     */
    @Cacheable(cacheNames = "user-UserMobileSave", key = "#entity.id")
    public Result<Boolean> save(UserMobileAddReq entity) {
        UserMobile baseEntity = BeanCopierTool.convert(entity, UserMobile.class);
        boolean bool = dbUserMobileService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_mobile getId
     */
    @Cacheable(cacheNames = "user-UserMobileById", key = "#id", sync = true)
    public Result<UserMobileResp> getById(Long id) {
        UserMobile baseEntity = dbUserMobileService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        UserMobileResp resp = BeanCopierTool.convert(baseEntity, UserMobileResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(resp);
    }

    /**
     * user_mobile 逻辑删除
     */
    @CacheEvict(value = "user-UserMobileById", key = "#id")
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserMobileService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_mobile id修改
     */
    @CacheEvict(value = "user-UserMobileById", key = "#entity.id")
    public Result<Boolean> updateById(UserMobileEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserMobile baseEntity = BeanCopierTool.convert(entity, UserMobile.class);
        return Result.createBySuccess(dbUserMobileService.updateById(baseEntity));
    }

    /**
     * user_mobile 修改
     */
    public Result<Boolean> update(List<UserMobileEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserMobile> baseEntities = convertService.copyList(entities, UserMobile.class);
        boolean bool = dbUserMobileService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_mobile getOne
     */
    public Result<UserMobileResp> getOne(UserMobileQueryReq entity) {
        UserMobile baseEntity = BeanCopierTool.convert(entity, UserMobile.class);
        UserMobile oneEntity = dbUserMobileService.getOne(baseEntity);
        UserMobileResp result = BeanCopierTool.convert(oneEntity, UserMobileResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_mobile List查询
     */
    public Result<List<UserMobileResp>> selectList(UserMobileQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserMobile baseEntity=BeanCopierTool.convert(entity,UserMobile.class);
        List<UserMobile> selectPage = dbUserMobileService.selectList(baseEntity);
        List<UserMobileResp> results = convertService.copyList(selectPage, UserMobileResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_mobile 分页查询
     */
    public Result<List<UserMobileResp>> selectPage(QueryPage<UserMobileQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserMobile> queryPage=convertService.convertQueryPage(entity,UserMobile.class);
        List<UserMobile> selectPage = dbUserMobileService.selectPage(queryPage);
        List<UserMobileResp> results = convertService.copyList(selectPage, UserMobileResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mobile in 查询
     */
    public <T> Result<List<UserMobileResp>> selectIn(QueryIn<UserMobileQueryReq, T> entity) {
        QueryIn<UserMobile, T> queryIn = convertService.convertIn(entity, UserMobile.class);
        List<UserMobile> entitys = dbUserMobileService.selectIn(queryIn);
        List<UserMobileResp> results = convertService.copyList(entitys, UserMobileResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mobile Bettween查询
     */
    public Result<List<UserMobileResp>> selectBetween(QueryBetween<UserMobileQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserMobile> between = convertService.convertBetween(entity, UserMobile.class);

        List<UserMobile> selectPage = dbUserMobileService.selectBetween(between);
        List<UserMobileResp> results = convertService.copyList(selectPage, UserMobileResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mobile 组合查询
     */
    public <T> Result<List<UserMobileResp>> selectQuery(QueryFull<UserMobileQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserMobile, T> queryFull = convertService.convertQueryFull(entity, UserMobile.class);
        List<UserMobile> selectPage = dbUserMobileService.selectQuery(queryFull);
        List<UserMobileResp> results = convertService.copyList(selectPage, UserMobileResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mobile like查询
     * @param entity
     * @return
     */
    public  Result<List<UserMobileResp>> selectLike(QueryLike<UserMobileQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserMobile> pageEntity = convertService.convertLike(entity,UserMobile.class);
        pageEntity.setSelect("id,name");
       List<UserMobile> selectPage=dbUserMobileService.selectLike(pageEntity);
        List<UserMobileResp> results = convertService.copyList(selectPage, UserMobileResp.class);
        return Result.createBySuccess(results);
    }
}
