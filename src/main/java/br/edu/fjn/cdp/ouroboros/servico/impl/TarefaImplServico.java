package br.edu.fjn.cdp.ouroboros.servico.impl;

import javax.inject.Inject;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.dao.SubtarefaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;
import br.edu.fjn.cdp.ouroboros.servico.TarefaServico;

public class TarefaImplServico implements TarefaServico {

	@Inject
	private SubtarefaDAO subtarefaDAO;
	@Inject
	private TarefaDAO tarefaDAO;

	public TarefaImplServico() {

	}

	@Override
	public Integer percentualPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
		double resultado = 0;
		double totalProjeto = 0;
		double totalPorEstado = 0;

		totalProjeto = subtarefaDAO.quantidadeDeSubtarefasPorProjeto(projeto);
		totalPorEstado = subtarefaDAO.quantidadeDeSubtarefasPorProjetoEEstado(projeto, estadoTarefa);

		if (totalProjeto > 0 && totalPorEstado > 0)
			resultado = ((totalPorEstado / totalProjeto) * 100);
		else
			resultado = this.percentualAtividadePorProjetoEEstado(projeto, estadoTarefa);

		return (int) resultado;
	}

	@Override
	public Integer percentualAtrasadoPorProjeto(Projeto projeto) {
		double resultado = 0;
		double totalProjeto = 0;
		double totalAtrasado = 0;

		totalProjeto = subtarefaDAO.quantidadeDeSubtarefasPorProjeto(projeto);
		totalAtrasado = subtarefaDAO.quantidadeDeSubtarefasAtrasadasPorProjeto(projeto);

		if (totalProjeto > 0 && totalAtrasado > 0)
			resultado = ((totalAtrasado / totalProjeto) * 100);
		else
			resultado = this.percentualAtividadeAtrasadaPorProjeto(projeto);

		return (int) resultado;
	}
	
	private Integer percentualAtividadePorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
		double resultado = 0;
		double totalProjeto = 0;
		double totalPorEstado = 0;
		
		totalProjeto = tarefaDAO.quantidadeDeTarefasPorProjeto(projeto);
		totalPorEstado = tarefaDAO.quantidadeDeTarefasPorProjetoEEstado(projeto, estadoTarefa);
		
		if (totalProjeto > 0 && totalPorEstado > 0)
			resultado = ((totalPorEstado / totalProjeto) * 100);
		else
			resultado = 0;
		
		return (int) resultado;
	}
	
	private Integer percentualAtividadeAtrasadaPorProjeto(Projeto projeto) {
		double resultado = 0;
		double totalProjeto = 0;
		double totalAtrasado = 0;

		totalProjeto = tarefaDAO.quantidadeDeTarefasPorProjeto(projeto);
		totalAtrasado = tarefaDAO.quantidadeDeTarefasAtrasadasPorProjeto(projeto);
		
		if (totalProjeto > 0 && totalAtrasado > 0) {
			resultado = ((totalAtrasado / totalProjeto) * 100);
		} else
			resultado = 0;
		
		return (int) resultado;
	}

}
