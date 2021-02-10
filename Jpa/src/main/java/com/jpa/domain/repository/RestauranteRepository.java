package com.jpa.domain.repository;

import java.util.List;

import com.jpa.domain.model.Restaurante;

public interface RestauranteRepository {

	public List<Restaurante> listar();
	public Restaurante retornaId(Long id);
	public Restaurante adicionar(Restaurante restaurante);
	public void remover(Long id); 
	
}
