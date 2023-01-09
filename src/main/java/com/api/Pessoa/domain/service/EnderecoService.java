package com.api.Pessoa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.Pessoa.domain.Exception.EntidadeNaoEncontradaException;
import com.api.Pessoa.domain.model.Endereco;
import com.api.Pessoa.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	private static final String MSG_NAO_EXISTE 
		= "não existe nenhum cadastro com o código %d";

	@Autowired
	private EnderecoRepository enderecoRepository; 
	

	@Transactional
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			enderecoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_NAO_EXISTE, id));
		}
	}
	
	@Transactional
	public Endereco buscar(Long id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_NAO_EXISTE, id)));
	}
		
}	

