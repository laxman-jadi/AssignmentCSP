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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Records == null) ? 0 : Records.hashCode());
		result = prime * result + responseCode;
		result = prime * result + ((responseMessage == null) ? 0 : responseMessage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response other = (Response) obj;
		if (Records == null) {
			if (other.Records != null)
				return false;
		} else if (!Records.equals(other.Records))
			return false;
		if (responseCode != other.responseCode)
			return false;
		if (responseMessage == null) {
			if (other.responseMessage != null)
				return false;
		} else if (!responseMessage.equals(other.responseMessage))
			return false;
		return true;
	}
	
	
}
