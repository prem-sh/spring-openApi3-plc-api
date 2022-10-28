package io.github.prem_sh.exceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.prem_sh.dto.ErrorMessageDto;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessageDto> entityNotfoundHandler(EntityNotFoundException e) {
		ErrorMessageDto dto = new ErrorMessageDto();
		dto.setErrorMessage(e.getMessage());
		dto.setDetails(e.getLocalizedMessage());
		return new ResponseEntity<ErrorMessageDto>(dto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<ErrorMessageDto> entityExistHandler(EntityExistsException e) {
		ErrorMessageDto dto = new ErrorMessageDto();
		dto.setErrorMessage(e.getMessage());
		dto.setDetails(e.getLocalizedMessage());
		return new ResponseEntity<ErrorMessageDto>(dto, HttpStatus.NOT_ACCEPTABLE);
	}
				
}
