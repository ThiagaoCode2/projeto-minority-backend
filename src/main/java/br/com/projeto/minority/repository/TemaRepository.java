package br.com.projeto.minority.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.minority.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>
{
	public List<Tema> findAllByDescricaoContainingIgnoreCase( String descricao );
}