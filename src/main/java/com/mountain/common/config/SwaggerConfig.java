package com.mountain.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 
* @Title: SwaggerConfig.java 
* @Package com.slarts.common.config 
* @Description: TODO api文档配置 
* @author porridge
* @date 2016年11月10日 下午2:38:28 
* @version V1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ParameterBuilder ticketPar = new ParameterBuilder();
    private List<Parameter> pars = new ArrayList<Parameter>();

    ApiInfo info = new ApiInfoBuilder()
            .title("接口文档")
            .description("接口文档")
            .version("1.0")
            .build();

    @Bean
    @SuppressWarnings("all")
    public Docket wx() {
        ticketPar.name("Authorization").description("jwt token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        ApiInfo info = new ApiInfoBuilder()
                .title("接口文档")
                .description("接口文档")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(info)
                .select()
                .paths(or(regex("/api/*.*")))
                .build()
                .globalOperationParameters(pars);
    }

    @Bean
    @SuppressWarnings("all")
    public Docket wx2() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("web")
                .apiInfo(info)
                .select()
                .paths(or(regex("/web/*.*")))
                .build()
                .globalOperationParameters(pars);
    }

    @Bean
    @SuppressWarnings("all")
    public Docket wx3() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("mitaotv")
                .apiInfo(info)
                .select()
                .paths(or(regex("/mitaotv/*.*")))
                .build()
                .globalOperationParameters(pars);
    }

}