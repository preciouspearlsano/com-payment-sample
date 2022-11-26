/**
 * 
 */
package com.payment.sample.controller.external;

import javax.transaction.Transactional;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.sample.dto.PayBookOptions;
import com.payment.sample.dto.PaymentBookDetails;
import com.payment.sample.dto.PaymentBookListView;
import com.payment.sample.dto.PaymentBookSearchRequest;
import com.payment.sample.dto.RequestSuccess;
import com.payment.sample.exception.PaymentSampleException;
import com.payment.sample.services.PaymentSampleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */
@RestController
@RequestMapping("/external")
@Transactional
@Tag(name = "Payment Book Sample")
public class PaymentSampleController {

	private final PaymentSampleService service;
	
	/**
	 * 
	 */
	public PaymentSampleController(PaymentSampleService service) {
		this.service = service;
	}
	
	@GetMapping("/loadBookPayList")
	@PreAuthorize("permitAll()")
	@Operation(summary = "FS-ID-PAY-001 | FS-ID-PAY-002", description = "|"
			+ "** API ID: ** FS-ID-PAY-001 | FS-ID-PAY-002 \\"
			+ "** CM: ** [CM-PAY-001] A1 \\"
			+ "** CM: ** [CM-PAY-001] A1a, A1b, A1c \\"
			+ "** FS: ** [FS-PAY-001] FS-ID-PAY-001 | FS-ID-PAY-002 \\"
			+ "Retrieve payment account list \\"
			+ "Retrieve payment account list by criteria \\"
			+ "** DM: ** \\"
			+ "   $responseBody.payload.content.bookList[0].paymentBookDetail.id = [DB] enroll_payment.id \\"
			+ "   $responseBody.payload.content.bookList[0].paymentBookDetail.bookName = [DB] enroll_payment.book_name \\"
			+ "   $responseBody.payload.content.bookList[0].paymentBookDetail.bankName = [DB] enroll_payment.bank_name \\"
			+ "   $responseBody.payload.content.bookList[0].paymentBookDetail.accountName = [DB] enroll_payment.account_name \\"
			+ "   $responseBody.payload.content.bookList[0].paymentBookDetail.accountNumber = [DB] enroll_payment.account_number \\")
	public ResponseEntity<PaymentBookListView> getBookPayList(
			@RequestHeader(name = "x-api-client-id", required=true) Integer apiClientId,
			@RequestHeader(name = "x-api-device-id", required=true) String apiDeviceId,
			@RequestHeader(name = "x-api-login-id", required=false) String apiLoginId,
			@RequestParam(name = "bookName", required=false) String bookName,
			@RequestParam(name = "bankName", required=false) String bankName,
			@RequestParam(name = "accountName", required=false) String accountName,
			@RequestParam(name = "accountNumber", required=false) String accountNumber) throws PaymentSampleException {
		var search = new PaymentBookSearchRequest();
		search.setBookName(ObjectUtils.toString(bookName,"%"));
		search.setBankName(ObjectUtils.toString(bankName,"%"));
		search.setAccountName(ObjectUtils.toString(accountName,"%"));
		search.setAccountNumber(ObjectUtils.toString(accountNumber,"%"));
		return ResponseEntity.ok(service.getBookPayList(search));
	}
	

	@GetMapping("/loadBookPayList/{id}")
	@PreAuthorize("permitAll()")
	@Operation(summary = "FS-ID-PAY-003", description = "|"
			+ "** API ID: ** FS-ID-PAY-003 \\"
			+ "** CM: ** [CM-PAY-002] A2 \\"
			+ "** FS: ** [FS-PAY-002] FS-ID-PAY-003 \\"
			+ "Get payment account record  \\"
			+ "** DM: ** \\"
			+ "   $responseBody.payload.content.paymentBookDetail.id = [DB] enroll_payment.id \\"
			+ "   $responseBody.payload.content.paymentBookDetail.bookName = [DB] enroll_payment.enr_book_name \\"
			+ "   $responseBody.payload.content.paymentBookDetail.bankName = [DB] enroll_payment.enr_bank_name \\"
			+ "   $responseBody.payload.content.paymentBookDetail.accountName = [DB] enroll_payment.enr_account_name \\"
			+ "   $responseBody.payload.content.paymentBookDetail.accountNumber = [DB] enroll_payment.enr_account_number \\")
	public ResponseEntity<PaymentBookDetails> loadBookPay(
			@RequestHeader(name = "x-api-client-id", required=true) Integer apiClientId,
			@RequestHeader(name = "x-api-device-id", required=true) String apiDeviceId,
			@RequestHeader(name = "x-api-login-id", required=false) String apiLoginId,
			@PathVariable(name = "id", required=false) String id) throws PaymentSampleException {
		return ResponseEntity.ok(service.loadBookPay(id));
	}
	

	@GetMapping("/loadBookPayOption")
	@PreAuthorize("permitAll()")
	@Operation(summary = "FS-ID-PAY-004", description = "|"
			+ "** API ID: ** FS-ID-PAY-004 \\"
			+ "** CM: ** [CM-PAY-003] A2 \\"
			+ "** FS: ** [FS-PAY-003] FS-ID-PAY-004 \\"
			+ "Retrieve book pay option list  \\"
			+ "** DM: ** \\"
			+ "   $responseBody.payload.content.payBookOptions.bankList[0].name = [DB] common_bank.bank_name \\")
	public ResponseEntity<PayBookOptions> loadBookPayOption(
			@RequestHeader(name = "x-api-client-id", required=true) Integer apiClientId,
			@RequestHeader(name = "x-api-device-id", required=true) String apiDeviceId,
			@RequestHeader(name = "x-api-login-id", required=false) String apiLoginId,
			@PathVariable(name = "id", required=false) String id) throws PaymentSampleException {
		return ResponseEntity.ok(service.loadBookPayOption());
	}
	

	@GetMapping("/submitBookPay")
	@PreAuthorize("permitAll()")
	@Operation(summary = "FS-ID-PAY-004", description = "|"
			+ "** API ID: ** FS-ID-PAY-004 \\"
			+ "** CM: ** [CM-PAY-003] A2 \\"
			+ "** FS: ** [FS-PAY-003] FS-ID-PAY-004 \\"
			+ "Retrieve book pay option list  \\"
			+ "** DM: ** \\"
			+ "   $responseBody.payload.content.payBookOptions.bankList[0].name = [DB] common_bank.bank_name \\")
	public ResponseEntity<RequestSuccess> submitBookPay(
			@RequestHeader(name = "x-api-client-id", required=true) Integer apiClientId,
			@RequestHeader(name = "x-api-device-id", required=true) String apiDeviceId,
			@RequestHeader(name = "x-api-login-id", required=false) String apiLoginId,
			@RequestBody PaymentBookDetails request) throws PaymentSampleException {
		return ResponseEntity.ok(service.submitBookPay(request));
	}
	
}
