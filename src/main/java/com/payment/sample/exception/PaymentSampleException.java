/**
 * 
 */
package com.payment.sample.exception;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.http.HttpStatus;

import com.payment.sample.dto.PayloadMsg;
import com.payment.sample.util.i18nUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentSampleException extends RuntimeException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus error = HttpStatus.BAD_REQUEST;
	private transient Object code;
	private transient Object payload;
	private transient PayloadMsg msg;

	/**
	 * 
	 */
	public PaymentSampleException() {
		super();
	}

	public PaymentSampleException(Throwable cause) {
		super(cause);
	}
	
	public <T> PaymentSampleException(String key) {
		super(i18nUtil.getMessage(key,Locale.ENGLISH));
		this.msg = new PayloadMsg(key);
	}
	
	public <T> PaymentSampleException(String key, Throwable cause) {
		super(i18nUtil.getMessage(key,Locale.ENGLISH),cause);
		this.msg = new PayloadMsg(key);
	}
	
	public <T> PaymentSampleException(String key, Object[] args) {
		super(i18nUtil.getMessage(key,args,Locale.ENGLISH));
		this.msg = new PayloadMsg(key,args);
	}
	
}
