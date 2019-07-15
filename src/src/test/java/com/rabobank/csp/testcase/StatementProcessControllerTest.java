package com.rabobank.csp.testcase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.csp.constant.CSPConstants;
import com.rabobank.csp.controllers.StatementProcessController;
import com.rabobank.csp.domain.Response;
import com.rabobank.csp.factory.StatementFactory;
import com.rabobank.csp.processor.StatementProcessor;

public class StatementProcessControllerTest {
	@InjectMocks
	StatementProcessController statementProcessController;
	
	@Mock
	StatementFactory factory;
	
	@Mock
	StatementProcessor processor;
	
	@Test
	public void testHandleCSV() throws Exception {
		Response response = new Response();
		  File file = new File("src/test/java/resources/record.csv");
		    FileInputStream input = new FileInputStream(file);
		    MultipartFile multipartFile = new MockMultipartFile("file",
		            file.getName(), "application/vnd.ms-excel", IOUtils.toByteArray(input));
		  when(factory.getStatementProcessor(CSPConstants.FILE_TYPE_CSV)).thenReturn(processor);
		  verify(factory, times(1)).getStatementProcessor(CSPConstants.FILE_TYPE_CSV);
		  verify(processor, times(1)).process(multipartFile, response);
	}
	
	@Test
	public void testHandleXML() throws Exception {
		Response response = new Response();
		  File file = new File("src/test/java/resources/record.xml");
		    FileInputStream input = new FileInputStream(file);
		    MultipartFile multipartFile = new MockMultipartFile("file",
		            file.getName(), "text/xml", IOUtils.toByteArray(input));
		  when(factory.getStatementProcessor(CSPConstants.FILE_TYPE_XML)).thenReturn(processor);
		  verify(factory, times(1)).getStatementProcessor(CSPConstants.FILE_TYPE_XML);
		  verify(processor, times(1)).process(multipartFile, response);
	}
	

}
