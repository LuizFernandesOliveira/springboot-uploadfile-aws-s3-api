package com.uploadfile.configs;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.uploadfile.controllers"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(true)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Manipulação de arquivos com Armazenamento na AWS S3")
        .description(
            "Uma API que fornece fazer uma manipulação de arquivos com armazenamento na AWS S3")
        .version("1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
        .contact(
            new Contact(
                "Luiz Fernandes de Oliveira",
                "https://luizfernandesoliveiraoficial.vercel.app/",
                "luizfernandesoliveiraoficial@gmail.com"))
        .build();
  }
}
