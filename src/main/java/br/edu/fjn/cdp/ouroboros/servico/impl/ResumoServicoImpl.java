package br.edu.fjn.cdp.ouroboros.servico.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;
import br.edu.fjn.cdp.ouroboros.servico.ResumoServico;

public class ResumoServicoImpl implements ResumoServico {

	@Inject
	private TarefaDAO tarefaDAO;

	public ResumoServicoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Tarefa> buscarTarefasAtrasadasPorProjeto(Projeto projeto) {
		System.out.println("atrasadas");
		Double horasPassadas = 0.0;
		List<Tarefa> atrasadas = new ArrayList<>();

		List<Tarefa> tarefas = tarefaDAO.buscarPorProjeto(projeto);

		for (Tarefa tarefa : tarefas) {
			horasPassadas = ((projeto.getFim() - projeto.getComeco()) * getDiasDeTrabalho(tarefa.getInicio(), projeto));
			if (tarefa.getNumeroHoras() < horasPassadas)
				atrasadas.add(tarefa);
		}

		System.out.println("tarefas atrasadas " + atrasadas.size());

		return atrasadas;
	}

	@Override
	public Integer percentualTarefasAtrasadasPorProjeto(Projeto projeto) {
		double resultado = 0;
		double totalProjeto = 0;
		double totalAtrasado = 0;

		totalProjeto = tarefaDAO.quantidadeDeTarefasPorProjeto(projeto);
		totalAtrasado = this.buscarTarefasAtrasadasPorProjeto(projeto).size();

		if (totalProjeto > 0 && totalAtrasado > 0) {
			resultado = ((totalAtrasado / totalProjeto) * 100);
		} else
			resultado = 0;

		return (int) resultado;
	}

	@Override
	public Integer percentualPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
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

	@Override
	public Integer numeroColaboradoresOciososPorProjeto(Projeto projeto) {
		int numero = 0;
		
		for (Usuario usuario : projeto.getEquipe().getColaboradores()) {
			if (!tarefaDAO.colaboradorEstaAlocado(usuario))
				++numero;
		}
		
		return numero;
	}

	@Override
	public List<Usuario> buscarColaboradoresOciososPorProjeto(Projeto projeto) {
		List<Usuario> ociosos = new ArrayList<>();

		return ociosos;
	}

	private int getDiasDeTrabalho(Calendar inicio, Projeto projeto) {
		int diasTrabalhados = 0;

		Calendar fim = Calendar.getInstance();

		if (inicio.getTime() == fim.getTime())
			return 1;

		do {
			for (int i : projeto.getSemana()) {
				if (inicio.get(Calendar.DAY_OF_WEEK) == i) {
					++diasTrabalhados;
				}
			}
			inicio.add(Calendar.DAY_OF_MONTH, 1);
		} while (inicio.getTimeInMillis() < fim.getTimeInMillis());

		return diasTrabalhados;
	}

}
