package br.com.projeto.minority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Tirar o exlclude após criação de Classe Security. - Thiago
@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
public class MinorityApplication 
{

	public static void main( String[] args ) 
	{
		SpringApplication.run( MinorityApplication.class, args );
	}

}
