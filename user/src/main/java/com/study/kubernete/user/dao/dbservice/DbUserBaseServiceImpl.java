package com.study.kubernete.user.dao.dbservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.dao.dbmapper.UserBaseMapper;
import com.study.kubernete.user.dao.dbmodel.UserBase;
import com.study.kubernete.user.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  用户
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Service
public class DbUserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> {
   @Autowired
   private CopyConvertServiceImpl convertService;
    public boolean update(List<UserBase> entities)
    {
        return super.update(entities.get(0), new QueryWrapper<UserBase>(entities.get(1)));
    }
    public UserBase getOne(UserBase entity) {
       return super.getOne(new QueryWrapper<UserBase>(entity),false);
    }
    public List<UserBase> selectList(UserBase entity) {

        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserBase> query = new QueryWrapper(entity);
        //if (id != null) {
        //query.lambda().ge(UserLoginRecord::getId, id);
        //}
        List<UserBase> selectPage = super.list( query);
        return selectPage;
    }
    /**
     * user_login_record 分页查询
     */
    public List<UserBase> selectPage(QueryPage<UserBase> entity) {
        Page<UserBase> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserBase> query = new QueryWrapper(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        //query.lambda().ge(UserBase::getId, id);
        //}
        IPage<UserBase> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserBase> selectIn(QueryIn<UserBase, T> entity) {
        QueryWrapper<UserBase> queryWrapper = new QueryWrapper<>(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
         {
            queryWrapper.select(entity.getSelect());
        }
        queryWrapper.in(entity.getColumnName(), entity.getInValues());

        List<UserBase> entitys = super.list(queryWrapper);

        return entitys;
    }
    public List<UserBase> selectBetween(QueryBetween<UserBase> entity)
    {
        Page<UserBase> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        QueryWrapper<UserBase> query = new QueryWrapper(entity.getData());
       if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        // query.lambda().ge(UserLoginRecord::getId, id);
        //}
        query = convertService.convertBetween(entity, query);
        IPage<UserBase> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserBase> selectQuery(QueryFull<UserBase, T> entity) {
        Page<UserBase> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件,根据实际条件及order修改，注意与order匹配
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        // entity.getData().setId(null);
        //}
        UserBase baseEntity = BeanCopierTool.convert(entity.getData(), UserBase.class);
        QueryWrapper<UserBase> query = new QueryWrapper(baseEntity);
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
        IPage<UserBase> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public List<UserBase> selectLike(QueryLike<UserBase> entity)
    {
        Page<UserBase> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserBase> query = new QueryWrapper(entity.getData());
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
        IPage<UserBase> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
}
