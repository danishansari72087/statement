package com.nagarro.statements.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nagarro.statements.constant.Constants;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseBody
	public   ResponseEntity<ExceptionResponse> apiCallException( InvalidRequestException exception) {
		logger.error(Constants.EXCEPTION, exception);
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setStatus(exception.getStatusCode());

		return new ResponseEntity<>(error,HttpStatus.valueOf(exception.getStatusCode()));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse>  missingServletRequestParameterException( MissingServletRequestParameterException exception) {
		logger.error(Constants.EXCEPTION, exception.getMessage());

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse>  methodArgumentNotValidException( MethodArgumentNotValidException exception) {
		logger.error(Constants.EXCEPTION, exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(Exception.class)
	@ResponseBody
	public   ResponseEntity<ExceptionResponse> handleException( Exception exception) {
		logger.error(Constants.EXCEPTION, exception);

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}