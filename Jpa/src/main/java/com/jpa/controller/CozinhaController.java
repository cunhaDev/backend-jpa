package com.jpa.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.jpa.domain.excessoes.EntidadeEmUsaoException;
import com.jpa.domain.excessoes.EntidadeNaoExisteExeption;
import com.jpa.domain.model.Cozinha;
import com.jpa.domain.repository.CozinhaRepository;
import com.jpa.domain.service.CozinhaRepositoryService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
@RequestMapping("/cozinhajpa/")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaRepositoryService cozinhaService;
	
	@GetMapping
	public List<Cozinha> listar()
	{
		return cozinhaRepository.listar();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cozinha> retornaId(@PathVariable Long id)
	{
		Cozinha cozinhaId = cozinhaRepository.retornaId(id);
		if(cozinhaId != null)
		{
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaId);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha)
	{
		Cozinha cozinhaCadastro = cozinhaService.adicionar(cozinha);
		return ResponseEntity.ok().body(cozinhaCadastro);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id)
	{
		try {
			cozinhaService.remover(id);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (EntidadeEmUsaoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			
		} catch (EntidadeNaoExisteExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Cozinha cozinha)
	{ 
			Cozinha cozinhaAtual = cozinhaRepository.retornaId(id);
			if(cozinhaAtual == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id", "restaurantes");
			cozinhaService.adicionar(cozinhaAtual);
			return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaAtual);
	}
}
