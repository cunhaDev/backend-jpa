package com.jpa.domain.excessoes;

import com.sun.jdi.event.ExceptionEvent;

public class EntidadeEmUsaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsaoException(String mensagem)
	{
		super(mensagem); 
	}
}
