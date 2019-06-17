package com.rabobank.csp.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.csp.constant.CSPConstants;
import com.rabobank.csp.domain.Response;
import com.rabobank.csp.domain.Record;
import com.rabobank.csp.services.ExtractorService;
import com.rabobank.csp.services.ValidatorService;

/**
 * 
 * @author Laxman Jadi
 *
 */

@RestController
@RequestMapping("/csp")
public class StatementProcessController {

	@Autowired
	private ValidatorService validatorService;

	@Autowired
	private ExtractorService extractorService;

	@RequestMapping(value = "/processStatment", method = RequestMethod.POST)
	public @ResponseBody Response handleFileUpload(@RequestParam("file") MultipartFile multipart) throws Exception {
		Response response = new Response();
		System.out.println("Content type is : "+multipart.getContentType());
		if (!multipart.isEmpty()) {
			if (multipart.getContentType().equalsIgnoreCase(CSPConstants.FILE_TYPE_CSV)) {
				List<Record> invalidRecords = new ArrayList<Record>();
				File csvFile = new File(multipart.getOriginalFilename());
				multipart.transferTo(csvFile);
				List<Record> extractedRecords = extractorService.extractCustomerStatmentFromCSV(csvFile);
				invalidRecords.addAll(validatorService.getRepeatedRecords(extractedRecords));
				invalidRecords.addAll(validatorService.getEndBalanceInvalidRecords(extractedRecords));
				if (!invalidRecords.isEmpty()) {
					response.setResponseCode(CSPConstants.SUCCESS_CODE);
					response.setResponseMessage(CSPConstants.VALIDATION_ERROR);
					response.setRecords(invalidRecords);
				} else {
					response.setResponseCode(CSPConstants.SUCCESS_CODE);
					response.setResponseMessage(CSPConstants.VALIDATION_ERROR);
				}
			} else if (multipart.getContentType().equalsIgnoreCase(CSPConstants.FILE_TYPE_XML)) {
				List<Record> invalidRecords = new ArrayList<Record>();
				File xmlFile = new File(multipart.getOriginalFilename());
				multipart.transferTo(xmlFile);
				List<Record> extractedRecords = extractorService.extractCustomerStatmentFromXML(xmlFile);
				invalidRecords.addAll(validatorService.getRepeatedRecords(extractedRecords));
				invalidRecords.addAll(validatorService.getEndBalanceInvalidRecords(extractedRecords));
				if (!invalidRecords.isEmpty()) {
					response.setResponseCode(CSPConstants.SUCCESS_CODE);
					response.setResponseMessage(CSPConstants.VALIDATION_ERROR);
					response.setRecords(invalidRecords);
				} else {
					response.setResponseCode(CSPConstants.SUCCESS_CODE);
					response.setResponseMessage(CSPConstants.VALIDATION_ERROR);
				}
			} else {
				response.setResponseCode(CSPConstants.INVALID_INPUT_CODE);
				response.setResponseMessage(CSPConstants.UNSUPORTED_FILE_FORMAT);
			}
		} else {
			response.setResponseCode(CSPConstants.INVALID_INPUT_CODE);
			response.setResponseMessage(CSPConstants.INVALID_INPUT);
		}
		return response;
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody Response handleException(HttpServletRequest request, Exception ex) {
		Response response = new Response();
		response.setResponseCode(CSPConstants.ERROR_CODE);
		response.setResponseMessage(CSPConstants.UNEXPECTED_SERVER_ERROR);
		return response;
	}

}
