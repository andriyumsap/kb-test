package com.kitabisa.app;

import com.kitabisa.app.util.ResponseTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kitabisa.app.util.Constan.DEFAULT_RESPONSE_DESC_ERROR;


@RestControllerAdvice
public class GlobalExceptionHandler {

	final private static Logger LOGGER = LogManager.getLogger();

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseTemplate exception(Exception e) {
		return new ResponseTemplate(false, DEFAULT_RESPONSE_DESC_ERROR, 300);
	}

}
