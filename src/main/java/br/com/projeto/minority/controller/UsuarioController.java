package br.com.projeto.minority.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.minority.model.Usuario;
import br.com.projeto.minority.model.UsuarioDTO;
import br.com.projeto.minority.repository.UsuarioRepository;
import br.com.projeto.minority.service.UsuarioService;
import jakarta.validation.Valid;


@RestController
@RequestMapping( "/usuarios" )
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class UsuarioController 
{
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping( "/todos" )
	public ResponseEntity<Object> buscarTodos( ) 
	{
		List<Usuario> listaUsuarios = repoUsuario.findAll( );
		
		if( listaUsuarios.isEmpty( ) ) 
		{
			return ResponseEntity.status( 204 ).build( );
		} 
		else 
		{
			return ResponseEntity.status( 200 ).body( listaUsuarios );
		}
	}
	
	@GetMapping( "/{idUsuario}" )
	public ResponseEntity<Usuario> buscarPorId( @PathVariable( value = "idUsuario" ) Long id ) 
	{
		Optional<Usuario> objetoUsuario = repoUsuario.findById( id );
		
		if( objetoUsuario.isPresent( ) ) 
		{
			return ResponseEntity.status( 200 ).body( objetoUsuario.get( ) );
		} 
		else 
		{
			return ResponseEntity.status( 204 ).build( );
		}
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<Usuario>> buscarPorNome( @RequestParam( defaultValue = "" ) String nome ) 
	{
		return ResponseEntity.status( 200 ).body( repoUsuario.findAllByNomeContainingIgnoreCase( nome ) );
	}
	
	@PostMapping( "/logar" )
	public ResponseEntity<UsuarioDTO> Autentication( @RequestBody Optional<UsuarioDTO> user )
	{
		return usuarioService.Logar( user ).map( resp -> ResponseEntity.ok( resp ) ).orElse( ResponseEntity.status (HttpStatus.UNAUTHORIZED ).build( ) );
	}
	
	@PostMapping( "/cadastrar" )
	public ResponseEntity<Usuario> Post( @RequestBody Usuario usuario )
	{
		return ResponseEntity.status( HttpStatus.CREATED ).body( usuarioService.CadastrarUsuario( usuario ) );
	}
	
	@PutMapping( "/alterar" )
	public ResponseEntity<Object> alterar( @Valid @RequestBody UsuarioDTO novoUsuario ) 
	{
		Optional<?> objetoAlterado = usuarioService.alterarUsuario( novoUsuario );

		if( objetoAlterado.isPresent( ) ) 
		{
			return ResponseEntity.status( 201 ).body( objetoAlterado.get( ) );
		} 
		else 
		{
			return ResponseEntity.status( 400 ).build( );
		}
	}
	
	@DeleteMapping( "/deletar/{idUsuario}" )
	public ResponseEntity<Object> deletarPorId( @PathVariable( value = "idUsuario" ) Long id ) 
	{
		Optional<Usuario> objetoExistente = repoUsuario.findById( id );
		
		if( objetoExistente.isPresent( ) ) 
		{
			repoUsuario.deleteById( id );
			
			return ResponseEntity.status( 200 ).build( );
		} 
		else 
		{
			return ResponseEntity.status( 400 ).build( );
		}
	}
}