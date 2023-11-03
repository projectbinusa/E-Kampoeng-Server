//package com.data.penduduk.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableSwagger2
//@EnableWebMvc
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("serverbyrtagihan.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo( apiInfo())
//                .securitySchemes(Arrays.asList(apiKey()))
//                .securityContexts(Arrays.asList(securityContext()));
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Api documentation",
//                "BayarTagihan documentation",
//                "1.0.0",
//                "",
//                "",
//                "",
//                "");
//    }
//}
