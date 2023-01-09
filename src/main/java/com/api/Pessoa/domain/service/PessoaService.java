package com.api.Pessoa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.Pessoa.domain.Exception.EntidadeEmUsoException;
import com.api.Pessoa.domain.Exception.EntidadeNaoEncontradaException;
import com.api.Pessoa.domain.model.Pessoa;
import com.api.Pessoa.domain.repository.PessoaRepository;
@Service
public class PessoaService {
	
	private static final String MSG_CODIGO_EM_USO 
		= "Pessoa com o Código %d não pode ser removida, pois está em uso";

	private static final String MSG_NAO_ENCONTRADO 
		= "não existe nenhum cadastro com o código %d";	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	 
	@Transactional
	public Pessoa buscar(Long id) {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_NAO_ENCONTRADO, id)));
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_NAO_ENCONTRADO, id));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CODIGO_EM_USO, id));
		}
	}
}
