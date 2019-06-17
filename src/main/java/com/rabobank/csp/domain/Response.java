package com.rabobank.csp.domain;

import java.util.List;

/**
 * 
 * @author Laxman Jadi
 *
 */
public class Response {
	private String responseMessage;
	private int responseCode;
	private List<Record> Records;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "Error [responseMessage=" + responseMessage + ", responseCode=" + responseCode + "]";
	}

	public List<Record> getRecords() {
		return Records;
	}

	public void setRecords(List<Record> records) {
		Records = records;
	}
}
