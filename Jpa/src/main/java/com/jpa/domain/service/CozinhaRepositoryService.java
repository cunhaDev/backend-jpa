package com.jpa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jpa.domain.excessoes.EntidadeEmUsaoException;
import com.jpa.domain.excessoes.EntidadeNaoExisteExeption;
import com.jpa.domain.model.Cozinha;
import com.jpa.domain.repository.CozinhaRepository;

@Service
public class CozinhaRepositoryService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha adicionar(Cozinha cozinha)
	{
		return cozinhaRepository.adicionar(cozinha);
	}
	
	public void remover(Long id)
	{
		try {
			cozinhaRepository.remover(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoExisteExeption(String.format
					("A cozinha do ID: %d não existe!", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsaoException(String.format
					("A cozinha do ID: %d está em uso, e não é possivél realizar essa ação!", id));
		} 
	}
	
}
