package com.rabobank.csp.services;

import java.util.List;

import com.rabobank.csp.domain.Record;
/**
 * 
 * @author Laxman Jadi
 *
 */
public interface ValidatorService {
	
	public List<Record> getRepeatedRecords(final List<Record> records);
	
	public List<Record> getEndBalanceInvalidRecords(final List<Record> records);

}
