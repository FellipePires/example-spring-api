package com.expcompany.spring_api.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.expcompany.spring_api.modelo.Pessoa;
import com.expcompany.spring_api.repositorio.RepositorioPessoa;

@RestController
@RequestMapping("/pessoas")
public class ControladorPessoa {
	
	@Autowired
	private RepositorioPessoa repositorioPessoa;
	
	@GetMapping
	public List<Pessoa> listar() {
		return repositorioPessoa.findAll();
	}
	
	@GetMapping("/{id_pessoa}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Integer id_pessoa) {
		Optional<Pessoa> pessoa = repositorioPessoa.findById(id_pessoa);
		
		return (pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastro")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa cadastrar(@Valid @RequestBody Pessoa pessoa) {
		String nome = pessoa.getNome();
		String email = pessoa.getEmail();
		
		if (repositorioPessoa.findByNomeAndEmail(nome, email).isPresent()) {
			String mensagemErro = "O nome e email desta pessoa já foram cadastrados";
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagemErro);
		}

		return repositorioPessoa.save(pessoa);
	}
	
	@PutMapping("/atualiza/{id_pessoa}")
	public Pessoa atualizar(@Valid @PathVariable Integer id_pessoa, @Valid @RequestBody Pessoa pessoa) {
		if (repositorioPessoa.findById(id_pessoa).isPresent()) {
			pessoa.setId_pessoa(id_pessoa);
			
			return repositorioPessoa.save(pessoa);
		}
		
		String mensagemErro = "A pessoa não foi encontrada";
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagemErro);
	}
	
	
	@DeleteMapping("/remove/{id_pessoa}")
	public ResponseEntity<String> remover(@Valid @PathVariable Integer id_pessoa) {
		
		if (repositorioPessoa.findById(id_pessoa).isPresent()) {
			repositorioPessoa.deleteById(id_pessoa);
			
			return ResponseEntity.ok("Pessoa removida com sucesso!");
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
