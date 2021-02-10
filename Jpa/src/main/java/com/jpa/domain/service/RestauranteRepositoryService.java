package com.jpa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jpa.domain.excessoes.EntidadeEmUsaoException;
import com.jpa.domain.excessoes.EntidadeNaoExisteExeption;
import com.jpa.domain.model.Cozinha;
import com.jpa.domain.model.Restaurante;
import com.jpa.domain.repository.CozinhaRepository;
import com.jpa.domain.repository.RestauranteRepository;

@Service
public class RestauranteRepositoryService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante adicionar(Restaurante restaurante)
	{
		Long id = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.retornaId(id);
		if(cozinha == null)
		{
			throw new EntidadeNaoExisteExeption(
					String.format
					("A Cozinha do ID: %d não existe. Favor insira uma cozinha existente!",
							id));
		}
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.adicionar(restaurante);
	}
	
	public void remover(Long id)
	{
		try {
			restauranteRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoExisteExeption(
					String.format("O Restaurante do ID: $d não existe!", id));
			
		} catch (EntidadeEmUsaoException e) {
			throw new EntidadeEmUsaoException(
					String.format("O Restaurante do ID: $d está em uso. Favor inserir outro que não esteja!", id));
		}
	}
}
