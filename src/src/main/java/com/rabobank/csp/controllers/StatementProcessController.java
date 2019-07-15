package com.rabobank.csp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.rabobank.csp.factory.StatementFactory;
import com.rabobank.csp.processor.StatementProcessor;

/**
 * 
 * @author Laxman Jadi
 *
 */

@RestController
@RequestMapping("/csp")
public class StatementProcessController {

	@Autowired
	private StatementFactory factory;

	static Logger logger = Logger.getLogger(StatementProcessController.class.getName());
	
	@RequestMapping(value = "/processStatment", method = RequestMethod.POST)
	public @ResponseBody Response handleFileUpload(@RequestParam("file") MultipartFile multipart) throws Exception {
		Response response = new Response();
		logger.info("Content type is : "+multipart.getContentType());
		if (!multipart.isEmpty()) {
			StatementProcessor processor = factory.getStatementProcessor(multipart.getContentType());
			if(null != processor) {
				processor.process(multipart, response);
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
		ex.printStackTrace();
		logger.info("Error "+ex.getMessage());
		Response response = new Response();
		response.setResponseCode(CSPConstants.ERROR_CODE);
		response.setResponseMessage(CSPConstants.UNEXPECTED_SERVER_ERROR);
		return response;
	}

}
