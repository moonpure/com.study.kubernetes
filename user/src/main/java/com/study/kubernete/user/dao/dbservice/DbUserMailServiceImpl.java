package com.study.kubernete.user.dao.dbservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.dao.dbmapper.UserMailMapper;
import com.study.kubernete.user.dao.dbmodel.UserMail;
import com.study.kubernete.user.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  邮箱用户
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Service
public class DbUserMailServiceImpl extends ServiceImpl<UserMailMapper, UserMail> {
   @Autowired
   private CopyConvertServiceImpl convertService;
    public boolean update(List<UserMail> entities)
    {
        return super.update(entities.get(0), new QueryWrapper<UserMail>(entities.get(1)));
    }
    public UserMail getOne(UserMail entity) {
       return super.getOne(new QueryWrapper<UserMail>(entity),false);
    }
    public List<UserMail> selectList(UserMail entity) {

        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserMail> query = new QueryWrapper(entity);
        //if (id != null) {
        //query.lambda().ge(UserLoginRecord::getId, id);
        //}
        List<UserMail> selectPage = super.list( query);
        return selectPage;
    }
    /**
     * user_login_record 分页查询
     */
    public List<UserMail> selectPage(QueryPage<UserMail> entity) {
        Page<UserMail> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserMail> query = new QueryWrapper(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        //query.lambda().ge(UserMail::getId, id);
        //}
        IPage<UserMail> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserMail> selectIn(QueryIn<UserMail, T> entity) {
        QueryWrapper<UserMail> queryWrapper = new QueryWrapper<>(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
         {
            queryWrapper.select(entity.getSelect());
        }
        queryWrapper.in(entity.getColumnName(), entity.getInValues());

        List<UserMail> entitys = super.list(queryWrapper);

        return entitys;
    }
    public List<UserMail> selectBetween(QueryBetween<UserMail> entity)
    {
        Page<UserMail> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        QueryWrapper<UserMail> query = new QueryWrapper(entity.getData());
       if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        // query.lambda().ge(UserLoginRecord::getId, id);
        //}
        query = convertService.convertBetween(entity, query);
        IPage<UserMail> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserMail> selectQuery(QueryFull<UserMail, T> entity) {
        Page<UserMail> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件,根据实际条件及order修改，注意与order匹配
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        // entity.getData().setId(null);
        //}
        UserMail baseEntity = BeanCopierTool.convert(entity.getData(), UserMail.class);
        QueryWrapper<UserMail> query = new QueryWrapper(baseEntity);
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
        IPage<UserMail> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public List<UserMail> selectLike(QueryLike<UserMail> entity)
    {
        Page<UserMail> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserMail> query = new QueryWrapper(entity.getData());
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
        IPage<UserMail> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
}
