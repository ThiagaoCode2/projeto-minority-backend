package br.com.projeto.minority.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.minority.model.Usuario;
import br.com.projeto.minority.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername( String userName ) throws UsernameNotFoundException 
	{
		Optional<Usuario> user = userRepository.findByUsuarioContainingIgnoreCase( userName );
		user.orElseThrow( ( ) -> new UsernameNotFoundException( userName + " not found. " ) );
			
		return user.map( UserDetailsImpl::new ).get( );
	}

}