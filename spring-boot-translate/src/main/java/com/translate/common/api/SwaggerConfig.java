package com.translate.common.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/10 11:46
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket mailApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("video管理").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.springbootvideo.web")).paths(PathSelectors.any()).build();
    }


    // 预览地址:swagger-ui.html
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring 中使用Swagger2构建文档").termsOfServiceUrl("http://www.zjjtv.top/")
                .contact(new Contact("正经吉", "http://www.zjjtv.top/", "zjjtv@gmail.com")).version("1.0").build();
    }
}
