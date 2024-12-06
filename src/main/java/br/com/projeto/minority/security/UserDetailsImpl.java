package br.com.projeto.minority.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.projeto.minority.model.Usuario;


public class UserDetailsImpl implements UserDetails
{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl( Usuario user ) 
	{
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl( ) 
	{
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return authorities;
	}

	@Override
	public String getPassword( ) 
	{
		return password;
	}

	@Override
	public String getUsername() 
	{
		return userName;
	}

}