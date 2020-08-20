package com.george.test.inter.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class BaseSwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.george.test.inter"))
				.build().apiInfo(metaData());

	}

	public ApiInfo metaData() {
		return new ApiInfoBuilder().title("Test").description("Inter Bank JAVA Test").version("1.0")
				.contact(new Contact("George Machado", "https://www.linkedin.com/in/george-machado-857889105/",
						"georgemichel41@hotmail.com"))
				.build();
	}
}
