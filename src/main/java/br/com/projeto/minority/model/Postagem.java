package br.com.projeto.minority.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "tb_postagens" )
public class Postagem 
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	private String titulo;
	
	@NotNull
	@Size( min = 10, max = 1000 )
	private String texto;
	
	@Temporal( TemporalType.TIMESTAMP )
	private Date date = new java.sql.Date( System.currentTimeMillis( ) );
	
	@ManyToOne 
	@JsonIgnoreProperties( "postagem" )
	private Tema tema;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JsonIgnoreProperties( { "minhasPostagens" } )
	private Usuario usuario;

	public long getId( ) 
	{
		return id;
	}

	public void setId( long id ) 
	{
		this.id = id;
	}

	public String getTitulo( ) 
	{
		return titulo;
	}

	public void setTitulo( String titulo ) 
	{
		this.titulo = titulo;
	}

	public String getTexto( ) 
	{
		return texto;
	}

	public void setTexto( String texto ) 
	{
		this.texto = texto;
	}

	public Date getDate( ) 
	{
		return date;
	}

	public void setDate( Date date ) 
	{
		this.date = date;
	}

	public Tema getTema( ) 
	{
		return tema;
	}

	public void setTema( Tema tema ) 
	{
		this.tema = tema;
	}

	public Usuario getUsuario( ) 
	{
		return usuario;
	}

	public void setUsuario( Usuario usuario ) 
	{
		this.usuario = usuario;
	}

}