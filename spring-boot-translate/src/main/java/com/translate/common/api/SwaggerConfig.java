package com.translate.common.api;

import com.translate.common.util.PropertiesUtils;
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
        return new Docket(DocumentationType.SWAGGER_2).groupName("translate管理").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.translate.web")).paths(PathSelectors.any()).build();
    }


    // 预览地址:swagger-ui.html
    private ApiInfo apiInfo() {
        String swagger_title = PropertiesUtils.APP.getProperty("swagger_title");
        String swagger_net = PropertiesUtils.APP.getProperty("swagger_net");
        String swagger_contact = PropertiesUtils.APP.getProperty("swagger_contact");
        String swagger_email = PropertiesUtils.APP.getProperty("swagger_email");

        return new ApiInfoBuilder().title(swagger_title).termsOfServiceUrl(swagger_net)
                .contact(new Contact(swagger_contact, swagger_net, swagger_email)).version("1.0").build();
    }
}
