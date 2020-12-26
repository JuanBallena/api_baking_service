package com.jp.baking.service.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.definition.ResponseDefinition;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Component
public class ServiceResponse {

	private String token;
	private Integer responseCode;
    private String responseMessage;
    private String type;
    private Object data;
    private Integer pages;
    private List<Object> errorsMessage;
	private Object exception;
	
	public ServiceResponse() {
		
		this.responseCode = ResponseDefinition.RESPONSE_CODE_INTERNAL_SERVER_ERROR;
		this.responseMessage = ResponseDefinition.RESPONSE_CODE_INTERNAL_SERVER_ERROR_S;
		this.token = "";
		this.type = "";
		this.data = null;
		this.pages = 0;
		this.errorsMessage = new ArrayList<Object>();
		this.exception = null;
	}
	
	public void addResponseOk() {	
		this.responseCode = ResponseDefinition.RESPONSE_CODE_OK;
		this.responseMessage = ResponseDefinition.RESPONSE_CODE_OK_S;
	}
	
	public void addResponseBadRequest() {
		this.responseCode = ResponseDefinition.RESPONSE_CODE_BAD_REQUEST;
		this.responseMessage = ResponseDefinition.RESPONSE_CODE_BAD_REQUEST_S;
	}
	
	public void addResponseNoContent() {
		this.responseCode = ResponseDefinition.RESPONSE_CODE_NO_CONTENT;
		this.responseMessage = ResponseDefinition.RESPONSE_CODE_NO_CONTENT_S;
	}
	
	public void addResponseCreated() {
		this.responseCode = ResponseDefinition.RESPONSE_CODE_CREATED;
		this.responseMessage = ResponseDefinition.RESPONSE_CODE_CREATED_S;
	}
}
