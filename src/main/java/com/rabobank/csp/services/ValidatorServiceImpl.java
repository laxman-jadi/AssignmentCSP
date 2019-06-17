package com.rabobank.csp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rabobank.csp.domain.Record;

/**
 * @author Laxman Jadi
 *  Date 17-June-2019
 *  This class is used for validating repeated records and end balance
 */

@Service
public class ValidatorServiceImpl implements ValidatorService {

	/**
	 * @return List<Records> to get repeated records.
	 */
	public List<Record> getRepeatedRecords(final List<Record> records) {
		Map<Integer, Record> identicalRecords = new HashMap<Integer, Record>();
		List<Record> repeatedRecords = new ArrayList<Record>();
		for (Record record : records) {
			if (identicalRecords.containsKey(record.getTransactionReference())) {
				repeatedRecords.add(record);
			} else {
				identicalRecords.put(record.getTransactionReference(), record);
			}
		}
		List<Record> finalRepeatedRecords = new ArrayList<Record>();
		finalRepeatedRecords.addAll(repeatedRecords);
		for (Record record : repeatedRecords) {
			if (null != identicalRecords.get(record.getTransactionReference())) {
				finalRepeatedRecords.add(identicalRecords.get(record.getTransactionReference()));
				identicalRecords.remove(record.getTransactionReference());
			}
		}
		return finalRepeatedRecords;
	}

	/**
	 * @return List<Records> 
	 */
	public List<Record> getEndBalanceInvalidRecords(final List<Record> records) {
		List<Record> endBalanceInvalidRecords = new ArrayList<Record>();
		for (Record record : records) {
			//if startbalance - mutation != endbalance then endbalance is invalid.
			if ((record.getStartBalance() - record.getMutation()) != record.getEndBalance()) {
				endBalanceInvalidRecords.add(record);
			}
		}
		return endBalanceInvalidRecords;
	}

}
