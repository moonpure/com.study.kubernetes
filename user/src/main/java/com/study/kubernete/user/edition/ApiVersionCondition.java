package com.study.kubernete.user.edition;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    private ApiVersionItem apiVersion;

    public ApiVersionCondition(ApiVersionItem apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 选择版本最大的接口
        return apiVersion.compareTo(other.apiVersion) >= 0 ? new ApiVersionCondition(apiVersion) : new ApiVersionCondition(other.apiVersion);
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        String apiversion = request.getHeader("appVersion");
        ApiVersionItem item = ApiVersionConverter.convert(apiversion);
        // 获取所有小于等于版本的接口
        if (item.compareTo(this.apiVersion) >= 0) {
            return this;
        }

        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 获取最大版本对应的接口
        return other.apiVersion.compareTo(this.apiVersion);
    }
}