package com.rabobank.csp.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Laxman Jadi
 *
 */
public class Record {

	private BigInteger transactionReference;
	private String accountNumber;
	private BigDecimal startBalance;
	private BigDecimal mutation;
	private String description;
	private BigDecimal endBalance;

	public Record() {
	}

	public Record(BigInteger transactionReference, String accountNumber, BigDecimal startBalance, BigDecimal mutation, String description,
			BigDecimal endBalance) {
		super();
		this.transactionReference = transactionReference;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
	}

	@XmlAttribute(name="reference")
	public BigInteger getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(BigInteger transactionRef) {
		this.transactionReference = transactionRef;
	}

	@XmlElement(name="accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@XmlElement(name="startBalance")
	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	@XmlElement(name="mutation")
	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name="endBalance")
	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endBalance == null) ? 0 : endBalance.hashCode());
		result = prime * result + ((mutation == null) ? 0 : mutation.hashCode());
		result = prime * result + ((startBalance == null) ? 0 : startBalance.hashCode());
		result = prime * result + ((transactionReference == null) ? 0 : transactionReference.hashCode());
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
		Record other = (Record) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endBalance == null) {
			if (other.endBalance != null)
				return false;
		} else if (!endBalance.equals(other.endBalance))
			return false;
		if (mutation == null) {
			if (other.mutation != null)
				return false;
		} else if (!mutation.equals(other.mutation))
			return false;
		if (startBalance == null) {
			if (other.startBalance != null)
				return false;
		} else if (!startBalance.equals(other.startBalance))
			return false;
		if (transactionReference == null) {
			if (other.transactionReference != null)
				return false;
		} else if (!transactionReference.equals(other.transactionReference))
			return false;
		return true;
	}
	
	

}
