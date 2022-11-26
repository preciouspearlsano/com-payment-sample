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
public class PaymentBookDetails {
	private String id;
	private String bookName;
	private String bankName;
	private String accountName;
	private String accountNumber;
}
