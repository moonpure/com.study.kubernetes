package com.study.kubernete.user.core;

import com.study.kubernete.user.edition.ApiVersionConverter;
import com.study.kubernete.user.edition.ApiVersionItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class RequestHeaderServiceImpl {
    @Autowired
    private Environment environment;

    public RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }

    public HttpServletRequest getHttpServletRequest() {
        try {
            RequestAttributes requestAttributes = getRequestAttributes();
            if (requestAttributes == null) {
                return null;
            }
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            if (servletRequestAttributes == null) {
                return null;
            }
            HttpServletRequest request = servletRequestAttributes.getRequest();
            // ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            // RequestContextHolder.setRequestAttributes(RequestContextHolder.getRequestAttributes(), true);
            return request;
        } catch (Exception ex) {

            return null;
        }
    }


    /**
     * 获取指定的headerkey
     *
     * @param headerKey
     * @return
     */
    public String getHeaderKey(String headerKey) {
        if (!StringUtils.hasText(headerKey)) {
            return null;
        }
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            log.debug("HttpServletRequest is null");
            return null;
        }
        try
        {
            return URLDecoder.decode( request.getHeader(headerKey),"utf-8");}
        catch (Exception ex)
        {

              return null;
        }
    }


    /**
     * 获取登录的用户Head字符串
     *
     * @return
     */
    public String getUserStr() {
        String userStr= getHeaderKey(RequestHeaderEnum.userHeaderkey.getCode());
        return userStr;
    }

    /**
     * 获取登录用户信息
     * @return
     */
    public UserBaseModel getUser()
    {

        String userStr=getUserStr();
        if(!StringUtils.hasText(userStr))
        {
            return  null;
        }
        String[] userpr= getUserArray(userStr);
        if(userpr==null||userpr.length<8)
        {
            return null;
        }
        UserBaseModel user=new UserBaseModel();
        user.setId(Long.parseLong(userpr[0]));
        user.setName(userpr[1]);
        user.setNickName(userpr[2]);
        user.setUserType(Long.parseLong(userpr[3]));
        user.setRoleId(Long.parseLong(userpr[4]));
        user.setCompanyId(Long.parseLong(userpr[5]));
        user.setCompanyName(userpr[6]);
        user.setCompanyUserId(Long.parseLong(userpr[7]));
        return user;
    }
    public  String[]  getUserArray(String userStr) {
        if (!StringUtils.hasText(userStr)) {
            return null;
        }
      return userStr.split("\\|");
    }

    /**
     * 取出版本号转数字
     * @return
     */
    public Long getAppVersion()
    {
        String appVersion = getAppVersionStr();
        return getAppVersion(appVersion);
    }
    public String getAppVersionStr()
    {
        return getHeaderKey(RequestHeaderEnum.appVersionHeaderKey.getCode());
    }

    /**
     * 取出版本号转数字
     * @return
     */
    public Long getAppVersion(String version)
    {
        if(!StringUtils.hasText(version))
        {
            return 10000L;
        }

        ApiVersionItem item = ApiVersionConverter.convert(version);
        return item.getHigh()*10000l+ item.getMid()*100+item.getLow();
    }
    /**
     * 取出小于等于指定版本号的版本号
     * @param list
     * @param version
     * @return
     */
    public Long getMaxVersion(List<Long> list, Long version)
    {
        if(version==null)
        {
            version=10000l;
        }
        if(list==null||list.size()<=0)
        {
            return version;
        }
        Long temVersion=list.stream().min(Comparator.comparing(num -> num)).get();
        for(Long item:list)
        {
            if(item==null)
            {
                continue;
            }
            if(item.equals(version))
            {
                return version;
            }
            if(item.compareTo(version)<0&&item.compareTo(temVersion)>0)
            {
                temVersion=item;
            }
        }
        return temVersion;
    }
}
