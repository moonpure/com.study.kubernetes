package com.study.kubernete.user.dao.dbservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.kubernete.user.copy.BeanCopierTool;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import com.study.kubernete.user.dao.dbmapper.UserWalletMapper;
import com.study.kubernete.user.dao.dbmodel.UserWallet;
import com.study.kubernete.user.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  用户钱包
 * </p>
 *
 * @author mybatisplusAutogenerator
 * @since 2021-10-18
 */
@Service
public class DbUserWalletServiceImpl extends ServiceImpl<UserWalletMapper, UserWallet> {
   @Autowired
   private CopyConvertServiceImpl convertService;
    public boolean update(List<UserWallet> entities)
    {
        return super.update(entities.get(0), new QueryWrapper<UserWallet>(entities.get(1)));
    }
    public UserWallet getOne(UserWallet entity) {
       return super.getOne(new QueryWrapper<UserWallet>(entity),false);
    }
    public List<UserWallet> selectList(UserWallet entity) {

        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserWallet> query = new QueryWrapper(entity);
        //if (id != null) {
        //query.lambda().ge(UserLoginRecord::getId, id);
        //}
        List<UserWallet> selectPage = super.list( query);
        return selectPage;
    }
    /**
     * user_login_record 分页查询
     */
    public List<UserWallet> selectPage(QueryPage<UserWallet> entity) {
        Page<UserWallet> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserWallet> query = new QueryWrapper(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        //query.lambda().ge(UserWallet::getId, id);
        //}
        IPage<UserWallet> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserWallet> selectIn(QueryIn<UserWallet, T> entity) {
        QueryWrapper<UserWallet> queryWrapper = new QueryWrapper<>(entity.getData());
        if(StringUtils.hasText(entity.getSelect()))
         {
            queryWrapper.select(entity.getSelect());
        }
        queryWrapper.in(entity.getColumnName(), entity.getInValues());

        List<UserWallet> entitys = super.list(queryWrapper);

        return entitys;
    }
    public List<UserWallet> selectBetween(QueryBetween<UserWallet> entity)
    {
        Page<UserWallet> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        QueryWrapper<UserWallet> query = new QueryWrapper(entity.getData());
       if(StringUtils.hasText(entity.getSelect()))
        {
        query.select(entity.getSelect());
        }
        //if (id != null) {
        // query.lambda().ge(UserLoginRecord::getId, id);
        //}
        query = convertService.convertBetween(entity, query);
        IPage<UserWallet> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public <T> List<UserWallet> selectQuery(QueryFull<UserWallet, T> entity) {
        Page<UserWallet> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件,根据实际条件及order修改，注意与order匹配
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        // entity.getData().setId(null);
        //}
        UserWallet baseEntity = BeanCopierTool.convert(entity.getData(), UserWallet.class);
        QueryWrapper<UserWallet> query = new QueryWrapper(baseEntity);
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
        IPage<UserWallet> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
    public List<UserWallet> selectLike(QueryLike<UserWallet> entity)
    {
        Page<UserWallet> pageEntity = convertService.convertPage(entity);
        pageEntity.setSearchCount(false);
        //优化，把id做大于条件
        Long id = null;
        //if (entity != null && entity.getData() != null || entity.getData().getId() != null) {
        //id = entity.getData().getId();
        //entity.getData().setId(null);
        //}
        QueryWrapper<UserWallet> query = new QueryWrapper(entity.getData());
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
        IPage<UserWallet> selectPage = super.page(pageEntity, query);
        return selectPage.getRecords();
    }
}
