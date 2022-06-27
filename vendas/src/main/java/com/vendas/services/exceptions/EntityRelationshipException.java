package com.vendas.services.exceptions;

public class EntityRelationshipException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityRelationshipException(String msg) {
		super(msg);
	}
	
	public EntityRelationshipException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
