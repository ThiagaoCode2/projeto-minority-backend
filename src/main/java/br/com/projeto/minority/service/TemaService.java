package br.com.projeto.minority.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.minority.model.Tema;
import br.com.projeto.minority.repository.TemaRepository;


@Service
public class TemaService 
{
	@Autowired
	private TemaRepository repoTema;
	
	public Optional<Tema> alterarTema( Tema temaParaAlterar ) 
	{
		return repoTema.findById( temaParaAlterar.getId( ) ).map( temaExistente -> {
			temaExistente.setDescricao( temaParaAlterar.getDescricao( ) );
			
			return Optional.ofNullable( repoTema.save( temaExistente ) );
			
		} ).orElseGet( ( ) -> {
			return Optional.empty( );
		} );
	}

}