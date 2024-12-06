package br.com.projeto.minority.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.projeto.minority.model.Usuario;
import br.com.projeto.minority.model.UsuarioDTO;
import br.com.projeto.minority.repository.UsuarioRepository;

@Service
public class UsuarioService 
{
	@Autowired
	private UsuarioRepository repoUsuario;
	
	public Usuario CadastrarUsuario( Usuario usuario ) 
	{
		BCryptPasswordEncoder enconder      = new BCryptPasswordEncoder( );
		String                senhaEnconder = enconder.encode( usuario.getSenha( ) );
		
		usuario.setSenha( senhaEnconder );
		
		return repoUsuario.save( usuario );
	}
	
	public Optional<UsuarioDTO> Logar( Optional<UsuarioDTO> user )
	{
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder( );
		Optional<Usuario>     usuario  = repoUsuario.findByUsuarioContainingIgnoreCase( user.get( ).getUsuario( ) );
		
		if( usuario.isPresent( ) ) 
		{
			if( enconder.matches( user.get( ).getSenha( ), usuario.get( ).getSenha( ) ) ) 
			{
				String auth        = user.get( ).getSenha( ) + " : " + user.get( ).getSenha( );
				byte[] encodedAuth = Base64.encodeBase64( auth.getBytes( Charset.forName( "US-ASCII" ) ) ); 
				String authHeader  = "Basic " + new String( encodedAuth );
				
				user.get( ).setToken( authHeader                );
				user.get( ).setNome ( usuario.get( ).getNome( ) );
				
				return user;
			}
			
		}
		
		return null;
	}
	
	public Optional<?> alterarUsuario( UsuarioDTO usuarioParaAlterar ) 
	{
		return repoUsuario.findById( usuarioParaAlterar.getId( ) ).map( usuarioExistente -> {
			BCryptPasswordEncoder encoder             = new BCryptPasswordEncoder( );
			String                senhaCriptografada  = encoder.encode( usuarioParaAlterar.getSenha( ) );

			usuarioExistente.setNome ( usuarioParaAlterar.getNome( ) );
			usuarioExistente.setSenha( senhaCriptografada            );
			
			return Optional.ofNullable( repoUsuario.save( usuarioExistente ) );
			
		} ).orElseGet( ( ) -> {
			return Optional.empty( );
		} );
	}
}