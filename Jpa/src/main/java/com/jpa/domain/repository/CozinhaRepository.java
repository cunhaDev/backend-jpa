package com.jpa.domain.repository;

import java.util.List;

import com.jpa.domain.model.Cozinha;

public interface CozinhaRepository {

	public List<Cozinha> listar();
	public Cozinha retornaId(Long id);
	public Cozinha adicionar(Cozinha cozinha);
	public void remover(long id); 
	
}
