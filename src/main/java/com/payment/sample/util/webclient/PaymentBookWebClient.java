/**
 * 
 */
package com.payment.sample.util.webclient;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.payment.sample.constant.PaymentSampleErrorCode;
import com.payment.sample.exception.PaymentSampleException;
import com.payment.sample.util.EndPoint;
import com.payment.sample.util.WebClientUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@Component
@Slf4j
public class PaymentBookWebClient {

	private final EndPoint endpoint;
	
	/**
	 * 
	 */
	public PaymentBookWebClient(EndPoint endpoint) {
		this.endpoint = endpoint;
	}
	
	/**
	 * @param map 
	 * @param requestJsonNode
	 * @return
	 */
	public JsonNode getPaymentBookSubmit(Map<String, String> map) throws PaymentSampleException{
		if (endpoint.isSwitchOn()) {
			try {
				var endpointWithBody = urlJoinMap(endpoint.getPoModuleActionBookPay(),map);
				return WebClientUtils.webClientGet(endpointWithBody);
			} catch (Exception e) {
				log.warn("ERR - {}", e.getMessage());
				throw new PaymentSampleException(PaymentSampleErrorCode.API_WEBCLIENT_ERROR.name());
			}
		}
		throw new PaymentSampleException(PaymentSampleErrorCode.API_WEBCLIENT_OFF_TEST_PURPOSES.name());
	}

	/**
	 * @param poModuleActionBookPay
	 * @param requestJsonNode
	 * @return
	 */
	@SuppressWarnings("all")
	private String urlJoinMap(String url, Map body) {
		var mapStr = new StringBuilder();
		if (!body.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = body.entrySet().iterator();
			var flag = true;
			while(iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if (flag)  {
					mapStr.append("?");
					flag = false;
				} else {
					mapStr.append("&");
				}
				mapStr.append(String.join("=",entry.getKey(),entry.getValue()));
			}
			
		}
		return mapStr.toString();
	}

}
