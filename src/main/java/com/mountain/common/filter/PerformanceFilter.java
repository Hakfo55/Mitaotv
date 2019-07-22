package com.mountain.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.filter
 * @Description: TODO 系统－性能测试拦截器
 * @date 2019/5/10
 */
public class PerformanceFilter implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(PerformanceFilter.class);
    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        logger.info("开始计时: {}  URI: {}", new SimpleDateFormat("HH:mm:ss.SSS").format(beginTime), httpServletRequest.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long beginTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        logger.info("请求地址：{} 计时结束：{}  耗时：{}ms  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m", httpServletRequest.getRequestURI(),
                new SimpleDateFormat("HH:mm:ss.SSS").format(endTime), (endTime - beginTime),
                httpServletRequest.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        print(httpServletRequest);
        //删除线程变量中的数据，防止内存泄漏
        startTimeThreadLocal.remove();
    }

    /**
     * 打印请求参数
     * @param request
     */
    private void print(HttpServletRequest request){
        Map map = request.getParameterMap();
        if (map != null && !map.isEmpty()) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                String[] values = (String[]) map.get(key);
                for (String value : values) {
                    logger.info("参数: " + key + "=" + value);
                }
            }
        }
    }
}
