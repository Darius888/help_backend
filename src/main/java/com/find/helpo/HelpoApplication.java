package com.find.helpo;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
public class HelpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpoApplication.class, args);
	}

	//TO-DO Configure to access images from different folder


	//TO-DO SWTICH TO AWS S3 SERVICES FOR IMAGE STORAGE

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/storage/**")
				.addResourceLocations("file:/home/darius/Idejos/Helpo/Backend/helpo/storage/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
}
	@Bean
	public WebMvcConfigurer cors() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200") //current angular host, later will be changed
						.allowCredentials(true)
						.allowedHeaders("*");
			}
		};
	}
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(64000);
		return loggingFilter;
	}
}

