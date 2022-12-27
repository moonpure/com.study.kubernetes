package com.study.kubernete.user.edition;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class ApiVerionHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return buildFrom(AnnotationUtils.findAnnotation(handlerType, ApiVersion.class));
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return buildFrom(AnnotationUtils.findAnnotation(method, ApiVersion.class));
    }

    private ApiVersionCondition buildFrom(ApiVersion platform) {
        return platform == null ? new ApiVersionCondition(new ApiVersionItem()) :
                new ApiVersionCondition(ApiVersionConverter.convert(platform.value()));
    }
}