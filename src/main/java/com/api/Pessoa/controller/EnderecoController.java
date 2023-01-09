package com.api.Pessoa.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.api.Pessoa.domain.model.Endereco;
import com.api.Pessoa.domain.repository.EnderecoRepository;
import com.api.Pessoa.domain.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public List<Endereco> listar(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Endereco buscar(@PathVariable Long id) {
		return enderecoService.buscar(id);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Endereco endereco) {
		 enderecoService.salvar(endereco);
	}
	
	@PutMapping("/{id}")
	public Endereco atualizar(@PathVariable Long id,
			@RequestBody Endereco endereco) {
		Endereco enderecoAtual = enderecoService.buscar(id);
		BeanUtils.copyProperties(endereco, enderecoAtual,"id");
		return enderecoService.salvar(enderecoAtual);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			enderecoService.excluir(id);
	}
	
	@GetMapping("/buscaPorPessoa/{pessoaId}")
	public ResponseEntity<List<Endereco>> ListaDeEnderecoPorPessoa(@PathVariable Long pessoaId){
		List<Endereco> enderecos = enderecoRepository.consultarPorPessoaId(pessoaId);
		if (enderecos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(enderecos);
	}
}
