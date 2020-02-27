package com.eteration.demo.productservice.config;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	BuildProperties buildProperties;

	@Value("${SPRING_PROFILES_ACTIVE:default}")
	String profile;
	
	@Value("${server.port:8080}")
	String port;

	
	@Bean
	public Docket api() {
	    String controllerPackage = buildProperties.getGroup();

		return new Docket(DocumentationType.SWAGGER_2)
				.ignoredParameterTypes(Principal.class)
				.select()
				.apis(RequestHandlerSelectors.basePackage(controllerPackage))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title(buildProperties.getName())
				//TODO format description
				.description(buildProperties.getArtifact()+":"+profile+":"+port+":"+buildProperties.getTime())
				.version(buildProperties.getVersion())
				.build();
	}
}