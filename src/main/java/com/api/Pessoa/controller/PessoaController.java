package com.api.Pessoa.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.api.Pessoa.domain.model.Pessoa;
import com.api.Pessoa.domain.repository.PessoaRepository;
import com.api.Pessoa.domain.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	@GetMapping("/{id}")
	public Pessoa buscar(@PathVariable Long id) {
		return pessoaService.buscar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		 return pessoaService.salvar(pessoa);
	}
	
	@PutMapping("/{id}")
	public Pessoa atualizar(@PathVariable Long id,
			@RequestBody Pessoa pessoa) {
		Pessoa pessoaAtual = pessoaService.buscar(id);
		BeanUtils.copyProperties(pessoa, pessoaAtual,"id");
		return  pessoaService.salvar(pessoaAtual);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			pessoaService.remover(id);
	}
	
}
