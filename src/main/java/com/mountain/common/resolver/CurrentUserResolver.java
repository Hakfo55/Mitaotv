package com.mountain.common.resolver;

import com.mountain.bo.UserBo;
import com.mountain.common.annotation.CurrentUser;
import com.mountain.common.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.resolver
 * @Description: TODO 系统－当前用户信息注入
 * @date 2019/5/10
 */
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    @Autowired
    UserHelper userHelper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserBo.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("Authorization");
        return userHelper.getUserInfo(token);
    }
}

