package com.mountain.common.filter;

import com.alibaba.fastjson.JSONObject;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.helper.UserHelper;
import com.mountain.common.util.response.GlobalStatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.filter
 * @Description: TODO 系统－token拦截器
 * @date 2019/5/10
 */
public class TokenFilter implements HandlerInterceptor {

    @Autowired
    UserHelper userHelper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }

        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(token)){
            throw new ServiceException(GlobalStatusCode.CODE_400001.code(), GlobalStatusCode.CODE_400001.value());
        }

        if (!userHelper.hasKey(token)) {
            throw new ServiceException(GlobalStatusCode.CODE_400001.code(), GlobalStatusCode.CODE_400001.value());
        }
        //以下五行注释可方便免权限访问
        String url = httpServletRequest.getServletPath();
        Boolean valid = valid(url, token);
        if (!valid) {
            throw new ServiceException(GlobalStatusCode.CODE_80005.code(), GlobalStatusCode.CODE_80005.value());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    /**
     * 校验权限
     *
     * @param token
     * @return
     */
    private Boolean valid(String url, String token) {
        String vaildUrl = userHelper.getValidUrl(token);
        if (vaildUrl == null) {
            return false;
        }
        try {
            Set<String> urls = new HashSet<>(JSONObject.parseArray(vaildUrl, String.class));
            return validUrl(url, urls);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验url
     * 算法描述
     * <p>
     * 例如：/web/account/role
     * 会生成
     * /web/account/role
     * /web/account/*
     * /web/* / *
     * 依次对比
     * @param url
     * @param sets 权限URL的集合
     * @return 有权限返回  true  没有权限返回 false
     */
    private Boolean validUrl(String url, Set<String> sets) throws ServiceException {
        if (sets == null) {
            return false;
        }
        String[] urls = url.split("/");
        for (int i = urls.length; i > 1; i--) {
            StringBuffer stb = new StringBuffer();
            stb.append("/");
            int len = urls.length;
            for (int j = 1; j < len; j++) {
                stb.append(j >= i ? "*" : urls[j]);
                if ((urls.length - 1) != j) {
                    stb.append("/");
                }
            }
            if (sets.contains(stb.toString())) {
                return true;
            }
        }
        return false;
    }
}
