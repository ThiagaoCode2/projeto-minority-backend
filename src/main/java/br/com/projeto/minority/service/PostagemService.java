package br.com.projeto.minority.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.minority.model.Postagem;
import br.com.projeto.minority.model.Tema;
import br.com.projeto.minority.repository.PostagemRepository;
import br.com.projeto.minority.repository.TemaRepository;
import br.com.projeto.minority.repository.UsuarioRepository;


@Service
public class PostagemService 
{
	@Autowired
	private  PostagemRepository repoPostegem;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private TemaRepository repoTema;
	
	public Optional<?> cadastrarPostagem( Postagem novaPostagem ) 
	{
		Optional<Tema> objetoExistente = repoTema.findById( novaPostagem.getTema( ).getId( ) );
		
		return repoUsuario.findById( novaPostagem.getUsuario( ).getId( ) ).map( usuarioExistente -> {
			if( objetoExistente.isPresent( ) ) 
			{
				novaPostagem.setUsuario( usuarioExistente       );
				novaPostagem.setTema   ( objetoExistente.get( ) );
				
				return Optional.ofNullable( repoPostegem.save( novaPostagem ) );
			} 
			else 
			{
				return Optional.empty( );
			}
			
		}).orElseGet( ( ) -> {
			return Optional.empty( );
		} );
	}
	
	public Optional<Postagem> alterarPostagem( Postagem postagemParaAlterar ) 
	{
		return repoPostegem.findById( postagemParaAlterar.getId( ) ).map( postagemExistente -> {
			postagemExistente.setTitulo( postagemParaAlterar.getTitulo( ) );
			postagemExistente.setTexto ( postagemParaAlterar.getTexto( )  );
			postagemExistente.setTema  ( postagemParaAlterar.getTema( )   );
			
			return Optional.ofNullable( repoPostegem.save( postagemExistente ) );
			
		} ).orElseGet( ( ) -> {
			return Optional.empty();
		} );
	}

}