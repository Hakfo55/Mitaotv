package com.mountain.common.filter;

import com.mountain.common.resolver.CurrentUserResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.filter
 * @Description: TODO 系统－系统拦截器配置中心
 * @date 2019/5/19
 */
@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(performanceFilter()).addPathPatterns("/**");
        registry.addInterceptor(tokenFilter()).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**").excludePathPatterns("/v2/api-docs");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
    }

    @Bean
    public CurrentUserResolver currentUserMethodArgumentResolver() {
        return new CurrentUserResolver();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TokenFilter tokenFilter(){return new TokenFilter();}

    @Bean
    public PerformanceFilter performanceFilter(){return new PerformanceFilter();}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}
