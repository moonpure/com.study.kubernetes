package com.study.kubernete.user.bllserver;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.core.ResultCode;
import com.study.kubernete.user.dao.dbmodel.UserRole;
import com.study.kubernete.user.dao.dbservice.DbUserRoleServiceImpl;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserRoleResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * user_role 用户类型
 */
@Service
public class UserRoleServiceImpl {
    @Resource
    private DbUserRoleServiceImpl dbUserRoleService;
    @Autowired
    private RequestHeaderServiceImpl headerService;
    @Autowired
    private CopyConvertServiceImpl convertService;

    /**
     * user_role 添加
     */
    @Cacheable(cacheNames = "user-UserRoleSave", key = "#entity.id")
    public Result<Boolean> save(UserRoleAddReq entity) {
        UserRole baseEntity = BeanCopierTool.convert(entity, UserRole.class);
        boolean bool = dbUserRoleService.save(baseEntity);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_role getId
     */
    @Cacheable(cacheNames = "user-UserRoleById", key = "#id", sync = true)
    public Result<UserRoleResp> getById(Long id) {
        UserRole baseEntity = dbUserRoleService.getById(id);
        if (baseEntity == null) {
            return Result.createFailure(ResultCode.DATABASE_QUERY_FAILURE);
        }
        UserRoleResp resp = BeanCopierTool.convert(baseEntity, UserRoleResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        return Result.createBySuccess(resp);
    }

    /**
     * user_role 逻辑删除
     */
    @CacheEvict(value = "user-UserRoleById", key = "#id")
    public Result<Boolean> deleteById(Long id) {
        boolean remove = dbUserRoleService.removeById(id);
        if (!remove) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(remove);
    }

    /**
     * user_role id修改
     */
    @CacheEvict(value = "user-UserRoleById", key = "#entity.id")
    public Result<Boolean> updateById(UserRoleEditReq entity) {
        // if (entity == null || entity.getId() == null) {
        // return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        // }
        UserRole baseEntity = BeanCopierTool.convert(entity, UserRole.class);
        return Result.createBySuccess(dbUserRoleService.updateById(baseEntity));
    }

    /**
     * user_role 修改
     */
    public Result<Boolean> update(List<UserRoleEditReq> entities) {
        if (entities == null || entities.size() != 2) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        List<UserRole> baseEntities = convertService.copyList(entities, UserRole.class);
        boolean bool = dbUserRoleService.update(baseEntities);
        if (!bool) {
            return Result.createFailure(ResultCode.DATABASE_EDIT_FAILURE);
        }
        return Result.createBySuccess(bool);
    }

    /**
     * user_role getOne
     */
    public Result<UserRoleResp> getOne(UserRoleQueryReq entity) {
        UserRole baseEntity = BeanCopierTool.convert(entity, UserRole.class);
        UserRole oneEntity = dbUserRoleService.getOne(baseEntity);
        UserRoleResp result = BeanCopierTool.convert(oneEntity, UserRoleResp.class);
        return Result.createBySuccess(result);
    }
    /**
     * user_role List查询
     */
    public Result<List<UserRoleResp>> selectList(UserRoleQueryReq entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        UserRole baseEntity=BeanCopierTool.convert(entity,UserRole.class);
        List<UserRole> selectPage = dbUserRoleService.selectList(baseEntity);
        List<UserRoleResp> results = convertService.copyList(selectPage, UserRoleResp.class);
        return Result.createBySuccess(results);
    }
    /**
     * user_role 分页查询
     */
    public Result<List<UserRoleResp>> selectPage(QueryPage<UserRoleQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryPage<UserRole> queryPage=convertService.convertQueryPage(entity,UserRole.class);
        List<UserRole> selectPage = dbUserRoleService.selectPage(queryPage);
        List<UserRoleResp> results = convertService.copyList(selectPage, UserRoleResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_role in 查询
     */
    public <T> Result<List<UserRoleResp>> selectIn(QueryIn<UserRoleQueryReq, T> entity) {
        QueryIn<UserRole, T> queryIn = convertService.convertIn(entity, UserRole.class);
        List<UserRole> entitys = dbUserRoleService.selectIn(queryIn);
        List<UserRoleResp> results = convertService.copyList(entitys, UserRoleResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_role Bettween查询
     */
    public Result<List<UserRoleResp>> selectBetween(QueryBetween<UserRoleQueryReq> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryBetween<UserRole> between = convertService.convertBetween(entity, UserRole.class);

        List<UserRole> selectPage = dbUserRoleService.selectBetween(between);
        List<UserRoleResp> results = convertService.copyList(selectPage, UserRoleResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_role 组合查询
     */
    public <T> Result<List<UserRoleResp>> selectQuery(QueryFull<UserRoleQueryReq, T> entity) {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryFull<UserRole, T> queryFull = convertService.convertQueryFull(entity, UserRole.class);
        List<UserRole> selectPage = dbUserRoleService.selectQuery(queryFull);
        List<UserRoleResp> results = convertService.copyList(selectPage, UserRoleResp.class);
        return Result.createBySuccess(results);
    }

    /**
     * user_role like查询
     * @param entity
     * @return
     */
    public  Result<List<UserRoleResp>> selectLike(QueryLike<UserRoleQueryReq> entity)
    {
        if (entity == null) {
            return Result.createFailure(ResultCode.PARAM_IS_INVALID);
        }
        QueryLike<UserRole> pageEntity = convertService.convertLike(entity,UserRole.class);
        pageEntity.setSelect("id,name");
       List<UserRole> selectPage=dbUserRoleService.selectLike(pageEntity);
        List<UserRoleResp> results = convertService.copyList(selectPage, UserRoleResp.class);
        return Result.createBySuccess(results);
    }
}
