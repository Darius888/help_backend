package com.find.helpo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class HelpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpoApplication.class, args);
	}

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

