package br.com.projeto.minority.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionManipulator 
{
	@ExceptionHandler
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	@ResponseBody
	public Map<String, String> mostrarMensagemCustomizada( Exception e ) 
	{
		Map<String, String> response = new HashMap<>();
		response.put( "status", "Entrada de dados invalida" );

		return response;
	}

}
