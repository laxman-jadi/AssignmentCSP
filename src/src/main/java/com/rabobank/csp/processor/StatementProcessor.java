package com.rabobank.csp.processor;

import org.springframework.web.multipart.MultipartFile;

import com.rabobank.csp.domain.Response;

public interface StatementProcessor {
	
	void process(MultipartFile multipart, Response response );
	
}
