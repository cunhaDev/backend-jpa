package com.jpa.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.domain.excessoes.EntidadeEmUsaoException;
import com.jpa.domain.excessoes.EntidadeNaoExisteExeption;
import com.jpa.domain.model.Restaurante;
import com.jpa.domain.repository.RestauranteRepository;
import com.jpa.domain.service.RestauranteRepositoryService;

@RestController
@RequestMapping("/restaurantejpa/")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteRepositoryService restauranteService;
	
	@GetMapping
	public List<Restaurante> listar()
	{
		return restauranteRepository.listar();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante)
	{
		try {
			restaurante = restauranteService.adicionar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante); 
		} catch (EntidadeNaoExisteExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Restaurante restaurante)
	{
		try {
			Restaurante restauranteAtual = restauranteRepository.retornaId(id);
			
			if(restauranteAtual == null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "cozinha_id", "endereco", "pagamentos", "cidade", "produtos");
			restauranteAtual = restauranteService.adicionar(restauranteAtual);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restauranteAtual);
		} catch (EntidadeNaoExisteExeption e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		} catch (EntidadeEmUsaoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<?> alteraParcial(@PathVariable Long id, @RequestBody Map<String, Object> campoOrigem)
	{
		Restaurante restauranteAtual = restauranteRepository.retornaId(id);
		
		if(restauranteAtual == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		merge(campoOrigem, restauranteAtual);
		
		return alterar(id, restauranteAtual);
		
	}

	private void merge(Map<String, Object> campoOrigem, Restaurante restauranteDestino) {
		campoOrigem.forEach((nomePropriedade, valorPropriedade) ->{
			
			ObjectMapper objectMapper = new ObjectMapper();
			Restaurante restauranteOrigem = objectMapper.convertValue(campoOrigem, Restaurante.class);
			
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
