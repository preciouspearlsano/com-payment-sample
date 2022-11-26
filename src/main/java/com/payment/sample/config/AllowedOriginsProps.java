/**
 * 
 */
package com.payment.sample.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cors")
@Data
public class AllowedOriginsProps {

	private boolean switchOn;
	
	private List<String> origins = new ArrayList<>();
}
