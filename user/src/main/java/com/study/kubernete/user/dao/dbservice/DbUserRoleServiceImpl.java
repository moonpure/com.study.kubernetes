package com.study.kubernete.user.dao.dbservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.dao.dbmapper.UserRoleMapper;
import com.study.kubernete.user.dao.dbmodel.UserRole;
import com.study.kubernete.user.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  用户类型
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Service
public class DbUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> {
   @Autowired
   private CopyConvertServiceImpl convertService;
    public boolean update(List<UserRole> entities)
    {
        return super.update(entities.get(0), new QueryWrapper<UserRole>(entities.get(1)));
    }
    public UserRole getOne(UserRole entity) {
       return super.getOne(new QueryWrapper<UserRole>(entity),false);
    }
    public List<UserRole> selectList(UserRole entity) {

        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserRole> query = new QueryWrapper(entity);
        //if (id != null) {
        //query.lambda().ge(UserLoginRecord::getId, id);
        //}
        List<UserRole> selectPage = super.list( query);
        return selectPage;
    }
    /**
     * user_login_record 分页查询
     */
    public List<UserRole> selectPage(QueryPage<UserRole> entity) {
        Page<UserRole> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserRole> query = new QueryWrapper(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        //query.lambda().ge(UserRole::getId, id);
        //}
        IPage<UserRole> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserRole> selectIn(QueryIn<UserRole, T> entity) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
         {
            queryWrapper.select(entity.getSelect());
        }
        queryWrapper.in(entity.getColumnName(), entity.getInValues());

        List<UserRole> entitys = super.list(queryWrapper);

        return entitys;
    }
    public List<UserRole> selectBetween(QueryBetween<UserRole> entity)
    {
        Page<UserRole> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        QueryWrapper<UserRole> query = new QueryWrapper(entity.getData());
       if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        // query.lambda().ge(UserLoginRecord::getId, id);
        //}
        query = convertService.convertBetween(entity, query);
        IPage<UserRole> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserRole> selectQuery(QueryFull<UserRole, T> entity) {
        Page<UserRole> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件,根据实际条件及order修改，注意与order匹配
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        // entity.getData().setId(null);
        //}
        UserRole baseEntity = BeanCopierTool.convert(entity.getData(), UserRole.class);
        QueryWrapper<UserRole> query = new QueryWrapper(baseEntity);
        if(StringUtils.hasText(entity.getSelect()))
        {
          query.select(entity.getSelect());
        }
        //if (id != null) {
        //query.lambda().ge(UserLoginRecord::getId, id);
        //}
        query = convertService.convertBetween(entity, query);

        if (!StringUtils.hasText(entity.getColumnName()) && entity.getInValues() != null && entity.getInValues().size() > 0) {
            query.in(entity.getColumnName(), entity.getInValues());
        }
        IPage<UserRole> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public List<UserRole> selectLike(QueryLike<UserRole> entity)
    {
        Page<UserRole> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserRole> query = new QueryWrapper(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
        {
           query.select(entity.getSelect());
        }
        if(StringUtils.hasText(entity.getColumnName())&&StringUtils.hasText(entity.getLikeValue()))
        {
            query.like(entity.getColumnName(),entity.getLikeValue());
        }
        //if (id != null) {
        //query.lambda().ge(CompanyOrg::getId, id);
        //}
        IPage<UserRole> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
}
