package com.study.kubernete.user.bllserver;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserMail;
import com.study.kubernete.user.dao.dbservice.DbUserMailServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserMailResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_mail 邮箱用户
 */
@Service
public class UserMailServiceImpl {
    @Resource
    private DbUserMailServiceImpl dbUserMailService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_mail 添加
     */
    @Cacheable(cacheNames = "user-UserMailSave", key = "#entity.id")
    public Result<Boolean> save(UserMailAddReq entity) {
        UserMail baseEntity = BeanCopierTool.convert(entity, UserMail.class);
        boolean bool = dbUserMailService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_mail getId
     */
    @Cacheable(cacheNames = "user-UserMailById", key = "#id", sync = true)
    public Result<UserMailResp> getById(Long id) {
        UserMail baseEntity = dbUserMailService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        UserMailResp resp = BeanCopierTool.convert(baseEntity, UserMailResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(resp);
    }

    /**
     * user_mail 逻辑删除
     */
    @CacheEvict(value = "user-UserMailById", key = "#id")
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserMailService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_mail id修改
     */
    @CacheEvict(value = "user-UserMailById", key = "#entity.id")
    public Result<Boolean> updateById(UserMailEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserMail baseEntity = BeanCopierTool.convert(entity, UserMail.class);
        return Result.createBySuccess(dbUserMailService.updateById(baseEntity));
    }

    /**
     * user_mail 修改
     */
    public Result<Boolean> update(List<UserMailEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserMail> baseEntities = convertService.copyList(entities, UserMail.class);
        boolean bool = dbUserMailService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_mail getOne
     */
    public Result<UserMailResp> getOne(UserMailQueryReq entity) {
        UserMail baseEntity = BeanCopierTool.convert(entity, UserMail.class);
        UserMail oneEntity = dbUserMailService.getOne(baseEntity);
        UserMailResp result = BeanCopierTool.convert(oneEntity, UserMailResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_mail List查询
     */
    public Result<List<UserMailResp>> selectList(UserMailQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserMail baseEntity=BeanCopierTool.convert(entity,UserMail.class);
        List<UserMail> selectPage = dbUserMailService.selectList(baseEntity);
        List<UserMailResp> results = convertService.copyList(selectPage, UserMailResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_mail 分页查询
     */
    public Result<List<UserMailResp>> selectPage(QueryPage<UserMailQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserMail> queryPage=convertService.convertQueryPage(entity,UserMail.class);
        List<UserMail> selectPage = dbUserMailService.selectPage(queryPage);
        List<UserMailResp> results = convertService.copyList(selectPage, UserMailResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mail in 查询
     */
    public <T> Result<List<UserMailResp>> selectIn(QueryIn<UserMailQueryReq, T> entity) {
        QueryIn<UserMail, T> queryIn = convertService.convertIn(entity, UserMail.class);
        List<UserMail> entitys = dbUserMailService.selectIn(queryIn);
        List<UserMailResp> results = convertService.copyList(entitys, UserMailResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mail Bettween查询
     */
    public Result<List<UserMailResp>> selectBetween(QueryBetween<UserMailQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserMail> between = convertService.convertBetween(entity, UserMail.class);

        List<UserMail> selectPage = dbUserMailService.selectBetween(between);
        List<UserMailResp> results = convertService.copyList(selectPage, UserMailResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mail 组合查询
     */
    public <T> Result<List<UserMailResp>> selectQuery(QueryFull<UserMailQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserMail, T> queryFull = convertService.convertQueryFull(entity, UserMail.class);
        List<UserMail> selectPage = dbUserMailService.selectQuery(queryFull);
        List<UserMailResp> results = convertService.copyList(selectPage, UserMailResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_mail like查询
     * @param entity
     * @return
     */
    public  Result<List<UserMailResp>> selectLike(QueryLike<UserMailQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserMail> pageEntity = convertService.convertLike(entity,UserMail.class);
        pageEntity.setSelect("id,name");
       List<UserMail> selectPage=dbUserMailService.selectLike(pageEntity);
        List<UserMailResp> results = convertService.copyList(selectPage, UserMailResp.class);
        return Result.createBySuccess(results);
    }
}
