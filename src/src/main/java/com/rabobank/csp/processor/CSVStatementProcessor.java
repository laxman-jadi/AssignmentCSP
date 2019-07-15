package com.rabobank.csp.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.csp.constant.CSPConstants;
import com.rabobank.csp.domain.Record;
import com.rabobank.csp.domain.Response;
import com.rabobank.csp.services.ExtractorService;
import com.rabobank.csp.services.ValidatorService;

@Service
public class CSVStatementProcessor implements StatementProcessor {
	
	@Autowired
	private ValidatorService validatorService;

	@Autowired
	private ExtractorService extractorService;

	@Override
	public void process(MultipartFile multipart, Response response) {
		
			List<Record> invalidRecords = new ArrayList<Record>();
			File csvFile = new File(multipart.getOriginalFilename());
			List<Record> extractedRecords;
			try {
				multipart.transferTo(csvFile);
				extractedRecords = extractorService.extractCustomerStatmentFromCSV(csvFile);
				invalidRecords.addAll(validatorService.getRepeatedRecords(extractedRecords));
				invalidRecords.addAll(validatorService.getEndBalanceInvalidRecords(extractedRecords));
			} catch (IllegalStateException iae) {
				response.setResponseCode(CSPConstants.IAE_CODE);
				response.setResponseMessage(CSPConstants.ILLEGAL_ARGUMENT_EXCEPTION);
			}  catch (IOException ioe) {
				response.setResponseCode(CSPConstants.IO_CODE);
				response.setResponseMessage(CSPConstants.IO_EXCEPTION);
			}  catch (Exception e1 ) {
				response.setResponseCode(CSPConstants.GENERAL_EXCEPTION_CODE);
				response.setResponseMessage(CSPConstants.EXCEPTION);
			}
			
			if (!invalidRecords.isEmpty()) {
				response.setResponseCode(CSPConstants.SUCCESS_CODE);
				response.setResponseMessage(CSPConstants.VALIDATION_ERROR);
				response.setRecords(invalidRecords);
			} else {
				response.setResponseCode(CSPConstants.SUCCESS_CODE);
				response.setResponseMessage(CSPConstants.VALIDATION_ERROR);
			}
		
	}

}
