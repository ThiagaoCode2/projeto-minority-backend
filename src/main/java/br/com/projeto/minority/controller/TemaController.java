package br.com.projeto.minority.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.minority.model.Tema;
import br.com.projeto.minority.repository.TemaRepository;


@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
@RequestMapping( "/tema" )
public class TemaController 
{
	@Autowired
	private TemaRepository repoTema;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll( )
	{
		return ResponseEntity.ok( repoTema.findAll( ) );
	}
	
	@GetMapping( "/{id}" )
	public ResponseEntity<Tema> GetById( @PathVariable Long id )
	{
		return repoTema.findById( id ).map( resp -> ResponseEntity.ok( resp ) ).orElse( ResponseEntity.notFound( ).build( ) );
	}
	
	@GetMapping( "/nome/{nome}" )
	public ResponseEntity<List<Tema>> GetByName( @PathVariable String nome )
	{
		return ResponseEntity.ok( repoTema.findAllByDescricaoContainingIgnoreCase( nome ) );
	
	}
	
	@PostMapping
	public ResponseEntity<Tema> post( @RequestBody Tema tema )
	{
		return ResponseEntity.status( HttpStatus.CREATED ).body( repoTema.save( tema ) );
	}
	
	@PutMapping
	public ResponseEntity<Tema> put( @RequestBody Tema tema )
	{
		return ResponseEntity.ok( repoTema.save( tema ) );
	}
	
	
	@DeleteMapping( "/{id}" )
	public void delete( @PathVariable Long id ) 
	{
		repoTema.deleteById( id );
	}
}