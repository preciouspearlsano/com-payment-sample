/**
 * 
 */
package com.payment.sample.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */

@Component
@Getter	
public class EndPoint {
	@Value("${endpoint.switchOn}")
	private boolean switchOn;
	
	@Value("${endpoint.po.domain.module.action.bookpay}")
	private String poModuleActionBookPay;
	
	@Value("${endpoint.po.domain.module.action.bookpayupt}")
	private String poModuleActionBookPayUpdt;
	
	
}
