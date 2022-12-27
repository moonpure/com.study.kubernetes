package com.study.kubernete.user.dao.dbservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.dao.dbmapper.UserWechatMapper;
import com.study.kubernete.user.dao.dbmodel.UserWechat;
import com.study.kubernete.user.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  微信用户
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Service
public class DbUserWechatServiceImpl extends ServiceImpl<UserWechatMapper, UserWechat> {
   @Autowired
   private CopyConvertServiceImpl convertService;
    public boolean update(List<UserWechat> entities)
    {
        return super.update(entities.get(0), new QueryWrapper<UserWechat>(entities.get(1)));
    }
    public UserWechat getOne(UserWechat entity) {
       return super.getOne(new QueryWrapper<UserWechat>(entity),false);
    }
    public List<UserWechat> selectList(UserWechat entity) {

        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserWechat> query = new QueryWrapper(entity);
        //if (id != null) {
        //query.lambda().ge(UserLoginRecord::getId, id);
        //}
        List<UserWechat> selectPage = super.list( query);
        return selectPage;
    }
    /**
     * user_login_record 分页查询
     */
    public List<UserWechat> selectPage(QueryPage<UserWechat> entity) {
        Page<UserWechat> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserWechat> query = new QueryWrapper(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        //query.lambda().ge(UserWechat::getId, id);
        //}
        IPage<UserWechat> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserWechat> selectIn(QueryIn<UserWechat, T> entity) {
        QueryWrapper<UserWechat> queryWrapper = new QueryWrapper<>(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
         {
            queryWrapper.select(entity.getSelect());
        }
        queryWrapper.in(entity.getColumnName(), entity.getInValues());

        List<UserWechat> entitys = super.list(queryWrapper);

        return entitys;
    }
    public List<UserWechat> selectBetween(QueryBetween<UserWechat> entity)
    {
        Page<UserWechat> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        QueryWrapper<UserWechat> query = new QueryWrapper(entity.getData());
       if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        // query.lambda().ge(UserLoginRecord::getId, id);
        //}
        query = convertService.convertBetween(entity, query);
        IPage<UserWechat> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserWechat> selectQuery(QueryFull<UserWechat, T> entity) {
        Page<UserWechat> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件,根据实际条件及order修改，注意与order匹配
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        // entity.getData().setId(null);
        //}
        UserWechat baseEntity = BeanCopierTool.convert(entity.getData(), UserWechat.class);
        QueryWrapper<UserWechat> query = new QueryWrapper(baseEntity);
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
        IPage<UserWechat> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public List<UserWechat> selectLike(QueryLike<UserWechat> entity)
    {
        Page<UserWechat> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserWechat> query = new QueryWrapper(entity.getData());
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
        IPage<UserWechat> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
}
