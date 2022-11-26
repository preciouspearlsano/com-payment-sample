/**
 * 
 */
package com.payment.sample.services;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.sample.constant.PaymentSampleErrorCode;
import com.payment.sample.dto.PayBookOptions;
import com.payment.sample.dto.PaymentBookDetails;
import com.payment.sample.dto.PaymentBookListView;
import com.payment.sample.dto.PaymentBookSearchRequest;
import com.payment.sample.dto.RequestSuccess;
import com.payment.sample.exception.PaymentSampleException;
import com.payment.sample.util.webclient.PaymentBookWebClient;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@Service
public class PaymentSampleService {

	private final PaymentBookWebClient paymentBookWebClient;
	
	/**
	 * 
	 */
	public PaymentSampleService(PaymentBookWebClient paymentBookWebClient) {
		this.paymentBookWebClient = paymentBookWebClient;
	}
	
	/**
	 * @param search 
	 * @return
	 */
	public PaymentBookListView getBookPayList(PaymentBookSearchRequest search) throws PaymentSampleException {
		throw new PaymentSampleException(PaymentSampleErrorCode.EXPAY_NO_FUNCTION_YET_INPROGRESS.name());
		//return new PaymentBookListView();
	}

	/**
	 * @param id
	 * @return
	 */
	public PaymentBookDetails loadBookPay(String id) throws PaymentSampleException  {
		throw new PaymentSampleException(PaymentSampleErrorCode.EXPAY_NO_FUNCTION_YET_INPROGRESS.name());
	}

	/**
	 * @return
	 */
	public PayBookOptions loadBookPayOption() throws PaymentSampleException  {
		throw new PaymentSampleException(PaymentSampleErrorCode.EXPAY_NO_FUNCTION_YET_INPROGRESS.name());
	}

	/**
	 * @param request
	 * @return
	 */
	@SuppressWarnings("all")
	public RequestSuccess submitBookPay(PaymentBookDetails request) throws PaymentSampleException {
		Map<String,String> map =  new ObjectMapper().convertValue(request, Map.class);
		JsonNode jsonNode = paymentBookWebClient.getPaymentBookSubmit(map);
		//do consumer business logic heree
		throw new PaymentSampleException(PaymentSampleErrorCode.EXPAY_NO_FUNCTION_YET_INPROGRESS.name());
		
	}

}
