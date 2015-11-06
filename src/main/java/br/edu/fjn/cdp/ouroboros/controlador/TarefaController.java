package br.edu.fjn.cdp.ouroboros.controlador;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;

public class TarefaController {

	@Inject
	private Result result;
	@Inject
	private TarefaDAO tarefaDAO;
	
	public TarefaController() {

	}
	
	
}
