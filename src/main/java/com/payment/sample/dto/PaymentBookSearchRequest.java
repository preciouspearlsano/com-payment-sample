/**
 * 
 */
package com.payment.sample.dto;

import lombok.Data;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@Data
public class PaymentBookSearchRequest {
	private String id;
	private String bookName;
	private String bankName;
	private String accountName;
	private String accountNumber;
	
	private String idin;
	private String bookNameIn;
	private String bankNameIn;
	private String accountNameIn;
	private String accountNumberIn;
}
