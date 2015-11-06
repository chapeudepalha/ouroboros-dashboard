package br.edu.fjn.cdp.ouroboros.servico.impl;

import javax.inject.Inject;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.dao.SubtarefaDAO;
import br.edu.fjn.cdp.ouroboros.servico.TarefaServico;

public class TarefaImplServico implements TarefaServico {

	@Inject
	private SubtarefaDAO subtarefaDAO;

	public TarefaImplServico() {

	}

	@Override
	public Long percentualPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
		long resultado = 0;
		long totalProjeto = 0;
		long totalPorEstado = 0;
		
		totalProjeto = subtarefaDAO.quantidadeDeSubtarefasPorProjeto(projeto);
		totalPorEstado = subtarefaDAO.quantidadeDeSubtarefasPorProjetoEEstado(projeto, estadoTarefa);
		
		resultado = ((totalPorEstado/totalProjeto)*100);
		
		return resultado;
	}

	@Override
	public Long percentualAtrasadoPorProjeto(Projeto projeto) {
		long resultado = 0;
		long totalProjeto = 0;
		long totalAtrasado = 0;
		
		totalProjeto = subtarefaDAO.quantidadeDeSubtarefasPorProjeto(projeto);
		totalAtrasado = subtarefaDAO.quantidadeDeSubtarefasAtrasadasPorProjeto(projeto);
		
		resultado = ((totalAtrasado/totalProjeto)*100);
		
		return resultado;
	}

}
