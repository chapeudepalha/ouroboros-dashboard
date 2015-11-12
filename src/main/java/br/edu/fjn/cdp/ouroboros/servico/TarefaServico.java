package br.edu.fjn.cdp.ouroboros.servico;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;

public interface TarefaServico {

	Integer percentualPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa);
	
	Integer percentualAtrasadoPorProjeto(Projeto projeto);
	
}
