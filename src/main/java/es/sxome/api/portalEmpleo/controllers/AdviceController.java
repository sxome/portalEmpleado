package es.sxome.api.portalEmpleo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.sxome.api.portalEmpleo.exceptions.RequestException;
import es.sxome.api.portalEmpleo.models.dto.ErrorDto;

@RestControllerAdvice
public class AdviceController {

	@ExceptionHandler(value = RequestException.class)
	public ResponseEntity<ErrorDto> requestExceptionHandler(RequestException ex){
		
		ResponseEntity<ErrorDto> response = null;
		
		ErrorDto error = new ErrorDto(ex.getCodigo(),ex.getMessage());
		
		switch(ex.getCodigo()) {
		
		case "400":
			response = new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
			break;
		case "404":
			response = new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
			break;
			
		}
		
		return response;
	}
}
