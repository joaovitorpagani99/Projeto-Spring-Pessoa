package com.api.Pessoa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.api.Pessoa.domain.model.Endereco;
import com.api.Pessoa.domain.model.Pessoa;
import com.api.Pessoa.domain.repository.EnderecoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class cadastroPessoaIntegrationsTests {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@Test( )
	public void testarCadastroPessoaComSucesso() {
		Pessoa novaPessoa = new Pessoa();
		
		novaPessoa.setNome("joao");
		novaPessoa.setDtNascimento("18/06/2001");
	
		
		assertThat(novaPessoa).isNotNull();
	}
	
	@Test
	public void testarCadastroEndereco() {
		Endereco endereco = new Endereco();
		
		endereco.setCep("1313");
		endereco.setCidade("cristalina");
		endereco.setLogradouro("cdaca");
		endereco.setNumero("62");
		
		endereco = enderecoRepository.save(endereco);
		
		assertThat(endereco).isNotNull();
		assertThat(endereco.getCidade()).isNotNull();
		assertThat(endereco.getCep()).isNotNull();
	}
}
