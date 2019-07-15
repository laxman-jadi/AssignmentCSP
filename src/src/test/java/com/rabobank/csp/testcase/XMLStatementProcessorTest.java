package com.rabobank.csp.testcase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.csp.domain.Record;
import com.rabobank.csp.domain.Response;
import com.rabobank.csp.processor.XMLStatementProcessor;
import com.rabobank.csp.services.ExtractorService;
import com.rabobank.csp.services.ExtractorServiceImpl;
import com.rabobank.csp.services.ValidatorService;
import com.rabobank.csp.services.ValidatorServiceImpl;

public class XMLStatementProcessorTest {

	@InjectMocks
	XMLStatementProcessor  xmlStatementProcessor;
	
	@Mock
	ValidatorService validatorService ;

	@Mock
	ExtractorService extractorService;

	  @Before
	   public void setUp(){
	      extractorService = mock(ExtractorServiceImpl.class);
	      validatorService = mock(ValidatorServiceImpl.class);
	   }

	
	@Test
	public void tesCSVStatementProcessor() throws Exception {
		  Response response = new Response();
		  List<Record> extractedRecords = new ArrayList<Record>();
		  Record record = new Record();
		  extractedRecords.add(record);
		 		  
		  File file = new File("src/test/java/resources/record.xml");
		    FileInputStream input = new FileInputStream(file);
		    MultipartFile multipartFile = new MockMultipartFile("file",
		            file.getName(), "text/xml", IOUtils.toByteArray(input));
		  when(extractorService.extractCustomerStatmentFromCSV(file)).thenReturn(extractedRecords);
		  when(validatorService.getRepeatedRecords(extractedRecords)).thenReturn(extractedRecords);
		  when(validatorService.getEndBalanceInvalidRecords(extractedRecords)).thenReturn(extractedRecords);
		  xmlStatementProcessor.process(multipartFile, response);
		  verify(extractorService, times(1)).extractCustomerStatmentFromXML(file);
		  verify(validatorService, times(1)).getEndBalanceInvalidRecords(extractedRecords);
		  verify(validatorService, times(1)).getRepeatedRecords(extractedRecords);
	}
	
}
