package br.edu.fjn.cdp.ouroboros.modelo.dao;

import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

public interface TarefaDAO extends DAOGenerico<Tarefa, Integer> {

	List<Tarefa> buscarPorProjeto(Projeto projeto);
	
	List<Tarefa> buscarPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa);
	
	Boolean colaboradorEstaAlocado(Usuario usuario);
		
	Long quantidadeDeTarefasPorProjeto(Projeto projeto);
	
	Long quantidadeDeTarefasPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa);
	
	Long quantidadeDeTarefasAtrasadasPorProjeto(Projeto projeto);
	
}