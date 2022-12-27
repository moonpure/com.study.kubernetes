package com.study.kubernete.user.core;

public enum RequestHeaderEnum {
    /* 成功状态码 */
    userHeaderkey("talentUser", "用户信息"),
    /* 失败状态码 */
    appVersionHeaderKey("appVersion", "应用程序版本号"),
    loginTypeHeaderKey("talentUserType", "登录类型"),
    tokenHeaderKey("talentAuthToken","tokenHeaderToken名");
    private String code;

    private String message;

    //3.定义构造函数
    RequestHeaderEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
    //4.定义get方法
    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
