package com.api.Pessoa.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.Pessoa.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	@Query("from Endereco where pessoa.id = :id")
	List<Endereco> consultarPorPessoaId(@Param("id") Long pessoaId);
}
