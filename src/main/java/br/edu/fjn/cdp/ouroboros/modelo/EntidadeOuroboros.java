package br.edu.fjn.cdp.ouroboros.modelo;

import java.io.Serializable;

public interface EntidadeOuroboros<I extends Serializable> extends Serializable {

	I getId();
	
	void setId(I id);
	
}
