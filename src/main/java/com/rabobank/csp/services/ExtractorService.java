package com.rabobank.csp.services;

import java.io.File;
import java.util.List;

import com.rabobank.csp.domain.Record;
/**
 * 
 * @author Laxman Jadi
 *
 */
public interface ExtractorService {

	public List<Record> extractCustomerStatmentFromCSV(File file) throws Exception;
	
	public List<Record> extractCustomerStatmentFromXML(File file) throws Exception;
	
}
