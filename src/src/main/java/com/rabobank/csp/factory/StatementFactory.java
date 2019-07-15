package com.rabobank.csp.factory;

import org.springframework.stereotype.Component;

import com.rabobank.csp.constant.CSPConstants;
import com.rabobank.csp.processor.CSVStatementProcessor;
import com.rabobank.csp.processor.StatementProcessor;
import com.rabobank.csp.processor.XMLStatementProcessor;

@Component
public class StatementFactory {
	
	public StatementProcessor getStatementProcessor(final String fileType) {
    	 
    	 if(CSPConstants.FILE_TYPE_CSV.equals(fileType)) {
    		 return new CSVStatementProcessor();
    	 } else if(CSPConstants.FILE_TYPE_XML.equals(fileType)) {
    		 return new XMLStatementProcessor();
    	 }
		return null;
    	 
     }

}
