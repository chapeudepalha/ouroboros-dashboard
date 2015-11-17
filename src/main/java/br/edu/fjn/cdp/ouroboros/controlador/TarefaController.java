package br.edu.fjn.cdp.ouroboros.controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.componentes.Sessao;
import br.edu.fjn.cdp.ouroboros.componentes.SomenteLogado;
import br.edu.fjn.cdp.ouroboros.modelo.Competencia;
import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.CompetenciaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;

@Controller
@Path("tarefa")
public class TarefaController {

	@Inject
	private Result result;
	@Inject
	private Sessao sessao;
	@Inject
	private TarefaDAO tarefaDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private CompetenciaDAO competenciaDAO;

	public TarefaController() {

	}

	@Get("novo/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void novo(Integer id) {
		List<Competencia> competencias = new ArrayList<>();
		competencias = competenciaDAO.listar();

		result.include("competencias", competencias);
		result.include("idProjeto", id);
	}

	@Post("cadastrar")
	@SomenteLogado
	public void cadastrar(Tarefa tarefa, String inicio) {
		tarefa.setEstadoTarefa(EstadoTarefa.PENDENTE);

		DateFormat converte = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		Date convertido = null;
		Calendar cal = null;

		try {
			inicio = formata.format(converte.parse(inicio));
			convertido = formata.parse(inicio);

			cal = Calendar.getInstance();
			cal.setTime(convertido);

			tarefa.setInicio(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tarefaDAO.inserir(tarefa);

		result.redirectTo(ProjetoController.class).gerenciar(tarefa.getProjeto().getId());
	}

	@Get("editar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void editar(Integer id) {
		List<Competencia> competencias = new ArrayList<>();
		competencias = competenciaDAO.listar();

		result.include("competencias", competencias);

		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		result.include("tarefa", tarefa);
	}

	@Get("visualizar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void visualizar(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		result.include("tarefa", tarefa);
	}
	
	@Post("editar")
	@SomenteLogado
	public void editar(Tarefa tarefa, String inicio) {
		DateFormat converte = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		Date convertido = null;
		Calendar cal = null;

		try {
			inicio = formata.format(converte.parse(inicio));
			convertido = formata.parse(inicio);

			cal = Calendar.getInstance();
			cal.setTime(convertido);

			tarefa.setInicio(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tarefaDAO.alterar(tarefa);
		result.redirectTo(ProjetoController.class).gerenciar(tarefa.getProjeto().getId());
	}

	@Get("remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void remover(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);
		tarefaDAO.remover(tarefa);
	}

	@Get("colaborador/add/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void addColaborador(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);
		List<Usuario> colaboradores = usuarioDAO.buscarPorTipoUsuarioECompetencia(TipoUsuario.COLABORADOR,
				tarefa.getCompetencia());

		result.include("tarefa", tarefa);
		result.include("colaboradores", colaboradores);
	}

	@Get("colaborador/alterar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void altColaborador(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);
		List<Usuario> colaboradores = usuarioDAO.buscarPorTipoUsuarioECompetencia(TipoUsuario.COLABORADOR,
				tarefa.getCompetencia());

		result.include("tarefa", tarefa);
		result.include("colaboradores", colaboradores);
	}

	@Post("colaborador")
	@SomenteLogado
	public void addColaborador(Usuario usuario, Tarefa tarefa) {
		tarefa = tarefaDAO.buscarPorId(tarefa.getId());
		usuario = usuarioDAO.buscarPorId(usuario.getId());

		tarefa.setEstadoTarefa(EstadoTarefa.AGUARDAACEITACAO);
		tarefa.setColaboradorResponsavel(usuario);

		tarefaDAO.alterar(tarefa);

		result.redirectTo(ProjetoController.class).gerenciar(tarefa.getProjeto().getId());
	}

	@Get("direita/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void direita(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		switch (tarefa.getEstadoTarefa()) {
		case PARAFAZER:
			alteraEstado(EstadoTarefa.EMPROGRESSO, tarefa);
			break;
		case EMPROGRESSO:
			alteraEstado(EstadoTarefa.CONCLUIDO, tarefa);
			break;
		default:
			break;
		}

		result.redirectTo(ProjetoController.class).gerenciar(tarefa.getProjeto().getId());
	}

	@Get("esquerda/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void esquerda(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		switch (tarefa.getEstadoTarefa()) {
		case EMPROGRESSO:
			alteraEstado(EstadoTarefa.PARAFAZER, tarefa);
			break;
		case CONCLUIDO:
			alteraEstado(EstadoTarefa.EMPROGRESSO, tarefa);
			break;
		default:
			break;
		}

		result.redirectTo(ProjetoController.class).gerenciar(tarefa.getProjeto().getId());
	}
	
	@Get("colaborador/direita/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void direitaColaborador(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		switch (tarefa.getEstadoTarefa()) {
		case PARAFAZER:
			alteraEstado(EstadoTarefa.EMPROGRESSO, tarefa);
			break;
		case EMPROGRESSO:
			alteraEstado(EstadoTarefa.CONCLUIDO, tarefa);
			break;
		default:
			break;
		}

		result.redirectTo(ProjetoController.class).painel(tarefa.getProjeto().getId());
	}

	@Get("colaborador/esquerda/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void esquerdaColaborador(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		switch (tarefa.getEstadoTarefa()) {
		case EMPROGRESSO:
			alteraEstado(EstadoTarefa.PARAFAZER, tarefa);
			break;
		case CONCLUIDO:
			alteraEstado(EstadoTarefa.EMPROGRESSO, tarefa);
			break;
		default:
			break;
		}

		result.redirectTo(ProjetoController.class).painel(tarefa.getProjeto().getId());
	}
	
	@Get("pendente")
	@SomenteLogado
	public void tarefasPendente() {
		List<Tarefa> pendentes = new ArrayList<>();
		pendentes = tarefaDAO.buscarPorColaboradorEEstado(sessao.getUsuario(), EstadoTarefa.AGUARDAACEITACAO);

		result.include("pendentes", pendentes);
	}
	
	@Get("aceitar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void aceitar(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		tarefa.setEstadoTarefa(EstadoTarefa.PARAFAZER);
		tarefaDAO.alterar(tarefa);
		result.redirectTo(ProjetoController.class).painel(tarefa.getProjeto().getId());
	}

	@Get("rejeitar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void rejeitar(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);

		tarefa.setEstadoTarefa(EstadoTarefa.PENDENTE);
		tarefa.setColaboradorResponsavel(null);
		
		tarefaDAO.alterar(tarefa);
		result.redirectTo(ProjetoController.class).painel(tarefa.getProjeto().getId());
	}

	private void alteraEstado(EstadoTarefa estadoTarefa, Tarefa tarefa) {
		tarefa.setEstadoTarefa(estadoTarefa);
		tarefaDAO.alterar(tarefa);
	}

}
