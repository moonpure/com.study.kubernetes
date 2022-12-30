package com.study.bootdocker;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
@RequestMapping("/api/userBase")
class UserBaseController {



    @GetMapping("/getid/{id}")
    public UserBaseResp getById(@PathVariable("id") Long id) {
//  http://localhost:58080/api/userBase/getid/1123456
        UserBase baseEntity = new UserBase();
        baseEntity.setId(id);
        baseEntity.setName("huangqijun");
        baseEntity.setNickName("moonpure");
        baseEntity.setName("黄启军");
        //BeanCopier beanCopier = BeanCopier.create(UserBase.class, UserBaseResp.class, false);
        UserBaseResp userBaseResp=new UserBaseResp();
       // beanCopier.copy(baseEntity, userBaseResp, null);   //UserBaseResp resp = BeanCopierTool.convert(baseEntity, UserBaseResp.class);
        // SensitiveTool.sensitive(resp);//脱敏
        userBaseResp.setId(id);
        userBaseResp.setNickName("moonpure");
        userBaseResp.setName("黄启军");
        userBaseResp.setMobile("139111222111");
        userBaseResp.setHeadimgurl("headUrl" );
        userBaseResp.setPassword("pwd");
        return userBaseResp;
    }

}