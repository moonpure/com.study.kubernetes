package com.study.kubernete.user.autoconfig;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.study.kubernete.user.core.CopyConvertServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public Sequence createSequence() {

        try {
            return new Sequence(InetAddress.getLocalHost());
        } catch (Exception ex) {
            return null;
        }
    }

    @Bean
    public MybatisPlusInterceptor getMybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
//        TenantLineInnerInterceptor tenantLineInnerInterceptor=new TenantLineInnerInterceptor();
//        TenantLineHandler tenantLineHandler=new TenantLineHandlerServceImpl() ;
//
//        tenantLineInnerInterceptor.setTenantLineHandler(tenantLineHandler);
//        mybatisPlusInterceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        PaginationInnerInterceptor pagination = new PaginationInnerInterceptor();
        pagination.setMaxLimit(500l);
        pagination.setDbType(DbType.MYSQL);
        mybatisPlusInterceptor.addInnerInterceptor(pagination);

        //防止全表更新与删除插件: BlockAttackInnerInterceptor
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(blockAttackInnerInterceptor);

//连表插件
        // MPJInterceptor mpjInterceptor = new MPJInterceptor();
        //  mybatisPlusInterceptor.addInnerInterceptor(mpjInterceptor);

        return mybatisPlusInterceptor;
    }
    @Bean
    public CopyConvertServiceImpl createMybatisPlusConvertServiceImpl() {
        return new CopyConvertServiceImpl();
    }
    @Bean
    public  FieldFillMetaObjectHandler createFieldFillMetaObjectHandler()
    {
        return new FieldFillMetaObjectHandler();
    }
}
