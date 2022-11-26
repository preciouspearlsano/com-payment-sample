/**
 * 
 */
package com.payment.sample.constant;

import lombok.Data;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */

@SuppressWarnings("unused")
public enum PaymentSampleErrorCode {

	API_ERROR,
	API_WEBCLIENT_ERROR,
	API_WEBCLIENT_OFF_TEST_PURPOSES,
	EXPAY_SAMPLE,
	EXPAY_NO_FUNCTION_YET_INPROGRESS("No function yet in progress."),
	EXPAY_NO_RECORD("No record found");
	
	private final String message;
	
	private PaymentSampleErrorCode() {
		this.message = "";
	}
	
	private PaymentSampleErrorCode(String message) {
		this.message = message;
	}
	
	private String getMessage() {
		return message;
	}
}
