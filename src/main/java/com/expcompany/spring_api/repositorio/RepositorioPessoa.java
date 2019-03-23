package com.expcompany.spring_api.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expcompany.spring_api.modelo.Pessoa;

public interface RepositorioPessoa extends JpaRepository<Pessoa, Integer> {
	
	Optional<Pessoa> findByNomeAndEmail(String nome, String email);
	
}
