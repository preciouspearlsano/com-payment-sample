/**
 * 
 */
package com.payment.sample.util;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.K;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.net.HttpHeaders;
import com.payment.sample.constant.PaymentSampleErrorCode;
import com.payment.sample.dto.GlobalSuccessResponse;
import com.payment.sample.exception.PaymentSampleException;

/**
 * @author Precious Pearl A. Sano Ventura <pr3_cious_15@yahoo.com>
 *
 */

public class WebClientUtils {
	private static WebClient webclient;
	
	/**
	 * 
	 */
	public WebClientUtils(WebClient webclient) {
		WebClientUtils.webclient = webclient;
	}
	
	private static WebClient getWebClient() {
		return getWebClient("");
	}

	/**
	 * @param string
	 * @return
	 */
	private static WebClient getWebClient(String urlStr) {
		var request = getHttpServletRequest();
		if (Objects.isNull(webclient)) {
			webclient = WebClient.create();
		}
		if (StringUtils.isBlank(urlStr) && StringUtils.isEmpty(request.getHeader(HttpHeaders.AUTHORIZATION))) {
			return webclient;
		}
		
		return webclient.mutate().baseUrl(urlStr)
				.defaultHeader(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION)).build();
	}

	/**
	 * @return
	 */
	private static HttpServletRequest getHttpServletRequest() {
		var ra = RequestContextHolder.getRequestAttributes();
		if (Objects.isNull(ra)) {
			throw new PaymentSampleException(PaymentSampleErrorCode.API_ERROR.name());
		}
		return ((ServletRequestAttributes)ra).getRequest();
	}

	/**
	 * @param apiUrl
	 * @param body
	 */
	@SuppressWarnings("hiding")
	public static <R,K> R getWebClientPayLoadPost(String apiUrl, K body) {
		return getWebClientPayLoadPost(getWebClient(), apiUrl, body);
	}

	/**
	 * @param webClient2
	 * @param apiUrl
	 * @param body
	 * @return
	 */
	@SuppressWarnings("hiding")
	public static <R, K> R getWebClientPayLoadPost(WebClient webClient, String apiUrl, K body) {
		GlobalSuccessResponse<R> globalSuccessResponse = webClient.post()
				.uri(apiUrl)
				.bodyValue(body)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<GlobalSuccessResponse<R>>() {
				})
				.block();
		
		if(Objects.isNull(globalSuccessResponse)) throw new PaymentSampleException(PaymentSampleErrorCode.API_ERROR.name());
		
		return globalSuccessResponse.getPayload();
	}

	/**
	 * @param apiUrl
	 */
	public static GlobalSuccessResponse<JsonNode> getWebClientJsonNode(String apiUrl) {
		return webclient
				.get()
				.uri(apiUrl)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<GlobalSuccessResponse<JsonNode>>() {
				})
				.block();
	}

	/**
	 * @param apiUrl
	 * @param body
	 */
	public static <R> R webClientGet(String apiUrlWithBody) {
		return webClientGet(getWebClient(), apiUrlWithBody);
		
	}

	/**
	 * @param webClient2
	 * @param apiUrlWithBody
	 * @return
	 */
	private static <R> R webClientGet(WebClient webClient2, String apiUrlWithBody) {
		return webclient
				.get()
				.uri(apiUrlWithBody)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<R>() {
				})
				.block();
	}
	
	
	
}
