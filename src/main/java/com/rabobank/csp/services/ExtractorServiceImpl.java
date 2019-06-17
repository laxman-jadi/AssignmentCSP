package com.rabobank.csp.services;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.rabobank.csp.domain.Record;
import com.rabobank.csp.domain.Records;

/**
 * 
 * @author Laxman Jadi
 *	This class is used to extract the uploaded file(csv/xml) and convert it into list of record java object.
 */
@Service
public class ExtractorServiceImpl implements ExtractorService {
	
	public List<Record> extractCustomerStatmentFromCSV(File file) throws Exception {
		HeaderColumnNameTranslateMappingStrategy<Record> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<Record>();
		beanStrategy.setType(Record.class);

		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("Reference", "transactionReference");
		columnMapping.put("AccountNumber", "accountNumber");
		columnMapping.put("Description", "description");
		columnMapping.put("Start Balance", "startBalance");
		columnMapping.put("Mutation", "mutation");
		columnMapping.put("End Balance", "endBalance");

		beanStrategy.setColumnMapping(columnMapping);

		CsvToBean<Record> csvToJavaBean = new CsvToBean<Record>();
		CSVReader reader = new CSVReader(new FileReader(file));
		List<Record> records = csvToJavaBean.parse(beanStrategy, reader);
		return records;
	}

	/**
	 * @return List<Records>
	 */
	public List<Record> extractCustomerStatmentFromXML(File file) throws Exception {
		  
        JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);  
   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Records rootRecord= (Records) jaxbUnmarshaller.unmarshal(file);  

		return rootRecord.getRecord();
	}

}
