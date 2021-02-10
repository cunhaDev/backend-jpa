package com.jpa.domain.excessoes;

public class EntidadeNaoExisteExeption extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoExisteExeption(String mensagem)
	{
		super(mensagem);
	}
}
