package br.edu.fjn.cdp.ouroboros.controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.componentes.SomenteLogado;
import br.edu.fjn.cdp.ouroboros.modelo.Equipe;
import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.EquipeDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.ProjetoDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.servico.TarefaServico;

@Controller
@Path("projeto")
public class ProjetoController {

	@Inject
	private Result result;
	@Inject
	private ProjetoDAO projetoDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private EquipeDAO equipeDAO;
	@Inject
	private TarefaDAO tarefaDAO;
	@Inject
	private TarefaServico tarefaServico;

	public ProjetoController() {

	}

	@Get("novo")
	@SomenteLogado
	public void novo() {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.CLIENTE);

		result.include("usuarios", usuarios);
	}

	@Post("cadastrar")
	@SomenteLogado
	public void cadastrar(Projeto projeto, String inicio, String entrega) {
		DateFormat converte = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		Date convertido = null;
		Calendar cal = null;

		try {
			inicio = formata.format(converte.parse(inicio));
			convertido = formata.parse(inicio);

			cal = Calendar.getInstance();
			cal.setTime(convertido);

			projeto.setInicio(cal);

			entrega = formata.format(converte.parse(entrega));
			convertido = formata.parse(entrega);
			cal = Calendar.getInstance();
			cal.setTime(convertido);

			projeto.setEntrega(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		projetoDAO.inserir(projeto);
		result.redirectTo(this).listar();
	}

	@Get("editar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void editar(Integer id) {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.CLIENTE);
		result.include("usuarios", usuarios);

		Projeto projeto = projetoDAO.buscarPorId(id);
		result.include("projeto", projeto);
	}

	@Post("editar")
	@SomenteLogado
	public void editar(Projeto projeto, String inicio, String entrega) {
		DateFormat converte = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		Date convertido = null;
		Calendar cal = null;

		try {
			inicio = formata.format(converte.parse(inicio));
			convertido = formata.parse(inicio);

			cal = Calendar.getInstance();
			cal.setTime(convertido);

			projeto.setInicio(cal);

			entrega = formata.format(converte.parse(entrega));
			convertido = formata.parse(entrega);
			cal = Calendar.getInstance();
			cal.setTime(convertido);

			projeto.setEntrega(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		projetoDAO.alterar(projeto);
		result.redirectTo(this).listar();
	}

	@Get("colaboradores/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void colaboradores(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.COLABORADOR);
		Set<Usuario> colaboradores = new HashSet<>();

		if (projeto.getEquipe() != null)
			if (projeto.getEquipe().getColaboradores() != null)
				colaboradores = projeto.getEquipe().getColaboradores();

		result.include("projeto", projeto);
		result.include("usuarios", usuarios);
		result.include("colaboradores", colaboradores);
	}

	@Post("colaborador/add")
	@SomenteLogado
	public void addColaborador(Usuario usuario, Projeto projeto) {
		Projeto proj = projetoDAO.buscarPorId(projeto.getId());
		Usuario u = usuarioDAO.buscarPorId(usuario.getId());

		if (proj.getEquipe() == null)
			proj.setEquipe(new Equipe());

		equipeDAO.adicionarColaborador(proj.getEquipe(), u);

		result.redirectTo(this).colaboradores(proj.getId());
	}

	@Get("colaborador/remover/{idColaborador:[0-9]{1,15}}/{idProjeto:[0-9]{1,15}}")
	@SomenteLogado
	public void removerColaborador(Integer idColaborador, Integer idProjeto) {
		Projeto p = projetoDAO.buscarPorId(idProjeto);
		Usuario u = usuarioDAO.buscarPorId(idColaborador);

		Equipe e = p.getEquipe();

		equipeDAO.removerColaborador(e, u);

		result.redirectTo(this).colaboradores(p.getId());
	}

	@Get("gerenciar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void gerenciar(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);

		long total = tarefaDAO.quantidadeDeTarefasPorProjeto(projeto);
		int atrasados = 0;
		int concluidos = 0;
		int restantes = 0;

		List<Tarefa> fazer = new ArrayList<>();
		List<Tarefa> progresso = new ArrayList<>();
		List<Tarefa> concluido = new ArrayList<>();

		if (total > 0) {
			concluidos = tarefaServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			restantes = 100 - concluidos;
			fazer = tarefaDAO.buscarPorProjetoEEstado(projeto, EstadoTarefa.PARAFAZER);
			progresso = tarefaDAO.buscarPorProjetoEEstado(projeto, EstadoTarefa.EMPROGRESSO);
			concluido = tarefaDAO.buscarPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			atrasados = tarefaServico.percentualAtrasadoPorProjeto(projeto);
		}

		result.include("total", total);
		result.include("concluidos", concluidos);
		result.include("restantes", restantes);
		result.include("atrasados", atrasados);
		result.include("fazer", fazer);
		result.include("progresso", progresso);
		result.include("concluido", concluido);
		result.include("projeto", projeto);
	}

	@Get("remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void remover(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);
		projetoDAO.remover(projeto);

		result.redirectTo(this).listar();
	}

	@Get("listar")
	@SomenteLogado
	public void listar() {
		List<Projeto> projetos = projetoDAO.listar();

		result.include("projetos", projetos);
	}

	@Get("resumo/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void resumo(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);

		long total = tarefaDAO.quantidadeDeTarefasPorProjeto(projeto);
		int atrasados = 0;
		int concluidos = 0;
		int restantes = 0;
		int fazer = 0;
		int progresso = 0;
		int concluido = 0;
		int dia = 0;

		if (total > 0) {
			concluidos = tarefaServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			restantes = 100 - concluidos;
			fazer = tarefaServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.PARAFAZER);
			progresso = tarefaServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.EMPROGRESSO);
			concluido = tarefaServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			atrasados = tarefaServico.percentualAtrasadoPorProjeto(projeto);
			dia = 100 - atrasados;
		}

		result.include("total", total);
		result.include("concluidos", concluidos);
		result.include("restantes", restantes);
		result.include("atrasados", atrasados);
		result.include("dia", dia);
		result.include("fazer", fazer);
		result.include("progresso", progresso);
		result.include("concluido", concluido);
		result.include("projeto", projeto);
	}

	@Get("tarefa/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void novaTarefa(Integer id) {
		result.forwardTo(TarefaController.class).novo(id);
	}

	@Post("tarefa/cadastrar")
	@SomenteLogado
	public void cadastrarTarefa(Tarefa tarefa, String inicio, String fim) {
		result.forwardTo(TarefaController.class).cadastrar(tarefa, inicio, fim);
	}

	@Get("tarefa/editar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void editarTarefa(Integer id) {
		result.forwardTo(TarefaController.class).editar(id);
	}

	@Post("tarefa/editar")
	@SomenteLogado
	public void editarTarefa(Tarefa tarefa, String inicio, String fim) {
		result.forwardTo(TarefaController.class).editar(tarefa, inicio, fim);
	}

	@Get("tarefa/remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void removerTarefa(Integer id) {
		result.forwardTo(TarefaController.class).remover(id);
	}
}
