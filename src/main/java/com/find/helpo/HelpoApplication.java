package com.find.helpo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@SpringBootApplication
public class HelpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpoApplication.class, args);
	}

	//TO-DO Configure to access images from different folder

//	@Configuration
//	public class StaticResourceConfiguration implements WebMvcConfigurer {
//
//		private final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//				"classpath:/META-INF/resources/", "classpath:/resources/",
//				"classpath:/static/", "classpath:/public/", "classpath:/storage/"};
//
//		@Override
//		public void addResourceHandlers(ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("/**")
//					.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//		}
//	}

	@Configuration
	@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
	@SecurityScheme(
			name = "bearerAuth",
			type = SecuritySchemeType.HTTP,
			bearerFormat = "JWT",
			scheme = "bearer"
	)
	public class OpenApi30Config {


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
}

