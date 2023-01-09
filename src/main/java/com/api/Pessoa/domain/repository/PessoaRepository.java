package com.api.Pessoa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.Pessoa.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
