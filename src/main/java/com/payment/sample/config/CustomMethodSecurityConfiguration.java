package com.payment.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@Primary
@Configuration
@EnableConfigurationProperties
@PropertySource(value="classpath:application.yaml")
public class CustomMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	
	@Value("${spring.profiles.active}")
	private String activeProfiles;
	
	@Bean
	public CorsFilter corsFilter() {
		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	/**
	 * @return
	 */
	private CorsConfiguration buildConfig() {
		var corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		return corsConfiguration;
	}
	
	
	@Bean
	public WebMvcConfigurer corsCofigurer(AllowedOriginsProps config) {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				if (config.isSwitchOn()) {
					var origins = config.getOrigins().toArray(String[]::new);
					registry.addMapping("/**").allowedOrigins(origins);	
				} else {
					registry.addMapping("/**")
					.allowedOriginPatterns("*")
					.allowedMethods("*")
					.allowedHeaders("*");
				} 
				
				WebMvcConfigurer.super.addCorsMappings(registry);
			}
		};
	}
}
