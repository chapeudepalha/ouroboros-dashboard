package br.edu.fjn.cdp.ouroboros.modelo.dao;

import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Subtarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;

public interface SubtarefaDAO extends DAOGenerico<Subtarefa, Integer> {

	List<Subtarefa> buscarPorTarefa(Tarefa tarefa);
	
	Long quantidadeDeSubtarefasPorTarefa(Tarefa tarefa);
	
	Long quantidadeDeSubtarefasPorProjeto(Projeto projeto);
	
	Long quantidadeDeSubtarefasAtrasadasPorProjeto(Projeto projeto);
	
	Long quantidadeDeSubtarefasPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa);
}