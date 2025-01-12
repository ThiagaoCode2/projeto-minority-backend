package br.com.projeto.minority.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "tb_usuario" )
public class Usuario 
{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank( message = "O campo nao pode ser vazio" )
	@Size( min = 2, max = 100 )
	private String nome;
	
	@NotBlank
	@Size( min = 5, max = 100 )
	private String usuario;
	
	@NotBlank
	@Size( min = 5, max = 100, message = "senha de 5 a 100" )
	private String senha;
	
	@Column( name = "dtNascimento" )
	@JsonFormat( pattern ="yyyy-MM-dd" )
	private LocalDate dataNascimento;
	
	@NotBlank (message = "obrigatório um tipo de usuario")
	private String tipoDeUsuario;
	
	private String foto;
	
	@OneToMany( mappedBy = "usuario", cascade = CascadeType.REMOVE )
	@JsonIgnoreProperties( {"usuario"} )
	private List<Postagem> minhasPostagens = new ArrayList<>();
	
	public Usuario( ) 
	{
		
	}

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

	public LocalDate getDataNascimento( ) 
	{
		return dataNascimento;
	}

	public void setDataNascimento( LocalDate dataNascimento ) 
	{
		this.dataNascimento = dataNascimento;
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

	public List<Postagem> getMinhasPostagens( ) 
	{
		return minhasPostagens;
	}

	public void setMinhasPostagens( List<Postagem> minhasPostagens ) 
	{
		this.minhasPostagens = minhasPostagens;
	}
}