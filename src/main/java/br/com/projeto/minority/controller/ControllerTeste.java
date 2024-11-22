package br.com.projeto.minority.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTeste 
{
	
	@GetMapping( "/hello" ) 
	public String sayHello( ) 
	{
		return "Hello World! Iniciando meu projeto de TCC";
	}

}
