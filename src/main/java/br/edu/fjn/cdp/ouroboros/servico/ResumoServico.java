package br.edu.fjn.cdp.ouroboros.servico;

import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

public interface ResumoServico {

	List<Tarefa> buscarTarefasAtrasadasPorProjeto(Projeto projeto);
	
	List<Usuario> buscarColaboradoresOciososPorProjeto(Projeto projeto);

	Integer numeroColaboradoresOciososPorProjeto(Projeto projeto);
	
	Integer percentualTarefasAtrasadasPorProjeto(Projeto projeto);
	
	Integer percentualPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa);
}
