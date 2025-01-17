package br.com.projeto.minority.model;

public class UsuarioDTO 
{
	private Long   id;
	private String nome;
	private String usuario;
	private String senha;
	private String token;
	private String tipoDeUsuario;
	private String foto;
	private String linkedin;
	private String profissao;
	private String empresa;
	
	public Long getId( ) 
	{
		return id;
	}
	
	public void setId( Long id ) 
	{
		this.id = id;
	}
	
	public String getNome( ) 
	{
		return nome;
	}
	
	public void setNome( String nome ) 
	{
		this.nome = nome;
	}
	
	public String getUsuario( ) 
	{
		return usuario;
	}
	
	public void setUsuario( String usuario ) 
	{
		this.usuario = usuario;
	}
	
	public String getSenha( ) 
	{
		return senha;
	}
	
	public void setSenha( String senha ) 
	{
		this.senha = senha;
	}
	
	public String getToken( ) 
	{
		return token;
	}
	
	public void setToken( String token ) 
	{
		this.token = token;
	}

	public String getTipoDeUsuario( ) 
	{
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario( String tipoDeUsuario ) 
	{
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public String getFoto( ) 
	{
		return foto;
	}

	public void setFoto( String foto ) 
	{
		this.foto = foto;
	}

	public String getLinkedin( ) 
	{
		return linkedin;
	}

	public void setLinkedin( String linkedin ) 
	{
		this.linkedin = linkedin;
	}

	public String getProfissao( ) 
	{
		return profissao;
	}

	public void setProfissao( String profissao ) 
	{
		this.profissao = profissao;
	}

	public String getEmpresa( ) 
	{
		return empresa;
	}

	public void setEmpresa( String empresa ) 
	{
		this.empresa = empresa;
	}
	
}