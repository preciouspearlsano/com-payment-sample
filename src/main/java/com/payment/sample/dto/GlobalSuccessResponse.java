/**
 * 
 */
package com.payment.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalSuccessResponse<T> {
	private boolean success;
	private int code;
	private T payload;
}
