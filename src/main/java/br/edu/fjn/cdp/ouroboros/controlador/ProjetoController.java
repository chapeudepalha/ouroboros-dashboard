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
import br.edu.fjn.cdp.ouroboros.componentes.Sessao;
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
import br.edu.fjn.cdp.ouroboros.servico.ResumoServico;

@Controller
@Path("projeto")
public class ProjetoController {

	@Inject
	private Result result;
	@Inject
	private Sessao sessao;
	@Inject
	private ProjetoDAO projetoDAO;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private EquipeDAO equipeDAO;
	@Inject
	private TarefaDAO tarefaDAO;
	@Inject
	private ResumoServico resumoServico;

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
	public void cadastrar(Projeto projeto, String inicio, List<Integer> semana, Double comeco, Double fim) {
		DateFormat converte = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		Date convertido = null;
		Calendar cal = null;

		projeto.setSemana(semana);
		projeto.setComeco(comeco);
		projeto.setFim(fim);

		try {
			inicio = formata.format(converte.parse(inicio));
			convertido = formata.parse(inicio);

			cal = Calendar.getInstance();
			cal.setTime(convertido);

			projeto.setInicio(cal);

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

		String comeco = "", fim = "";
		Double fracaoComeco = 0.0, fracaoFim = 0.0, controle = 0.5;

		fracaoComeco = projeto.getComeco() % 1;
		fracaoFim = projeto.getFim() % 1;

		comeco = projeto.getComeco() + "";
		comeco = comeco.substring(0, comeco.indexOf('.'));

		fim = projeto.getFim() + "";
		fim = fim.substring(0, fim.indexOf('.'));

		if (fracaoComeco.compareTo(controle) == 0)
			comeco += ":30";
		else
			comeco += ":00";

		if (fracaoFim.compareTo(controle) == 0)
			fim += ":30";
		else
			fim += ":00";

		result.include("comeco", comeco);
		result.include("fim", fim);
	}

	@Post("editar")
	@SomenteLogado
	public void editar(Projeto projeto, String inicio, List<Integer> semana, Double comeco, Double fim) {
		DateFormat converte = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
		Date convertido = null;
		Calendar cal = null;

		projeto.setSemana(semana);
		projeto.setComeco(comeco);
		projeto.setFim(fim);

		try {
			inicio = formata.format(converte.parse(inicio));
			convertido = formata.parse(inicio);

			cal = Calendar.getInstance();
			cal.setTime(convertido);

			projeto.setInicio(cal);

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

	@Get("painel/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void painel(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);

		int total = 0;
		int maior = 0;

		List<Tarefa> tarefas = tarefaDAO.buscarPorProjetoEColaborador(projeto, sessao.getUsuario());

		List<Tarefa> aceitacao = new ArrayList<>();
		List<Tarefa> fazer = new ArrayList<>();
		List<Tarefa> progresso = new ArrayList<>();
		List<Tarefa> concluido = new ArrayList<>();

		total = tarefas.size();
		System.out.println("total de tarefas: "+total);
		if (total > 0) {
			for (Tarefa tarefa : tarefas) {
				switch (tarefa.getEstadoTarefa()) {
				case AGUARDAACEITACAO:
					aceitacao.add(tarefa);
					break;
				case PARAFAZER:
					fazer.add(tarefa);
					break;
				case EMPROGRESSO:
					progresso.add(tarefa);
					break;
				case CONCLUIDO:
					concluido.add(tarefa);
					break;
				default:
					break;
				}
			}
		}
		
		if ((aceitacao.size() > fazer.size()) || (aceitacao.size() > progresso.size())
				|| (aceitacao.size() > concluido.size()))
			maior = aceitacao.size();
		else if ((fazer.size() > progresso.size()) || (fazer.size() > concluido.size()))
			maior = fazer.size();
		else if (progresso.size() > concluido.size())
			maior = progresso.size();
		else
			maior = concluido.size();

		result.include("total", total);
		result.include("maior", maior);
		
		result.include("aceitacao", aceitacao);
		result.include("fazer", fazer);
		result.include("progresso", progresso);
		result.include("concluido", concluido);

		result.include("projeto", projeto);
	}

	@Get("gerenciar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void gerenciar(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);

		long total = 0;
		int atrasados = 0;
		int concluidos = 0;
		int restantes = 0;
		int maior = 0;

		concluidos = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
		restantes = 100 - concluidos;
		atrasados = resumoServico.percentualTarefasAtrasadasPorProjeto(projeto);

		List<Tarefa> tarefas = tarefaDAO.buscarPorProjeto(projeto);

		List<Tarefa> pendente = new ArrayList<>();
		List<Tarefa> fazer = new ArrayList<>();
		List<Tarefa> progresso = new ArrayList<>();
		List<Tarefa> concluido = new ArrayList<>();

		total = tarefas.size();
		if (total > 0) {
			for (Tarefa tarefa : tarefas) {
				switch (tarefa.getEstadoTarefa()) {
				case PENDENTE:
					pendente.add(tarefa);
					break;
				case AGUARDAACEITACAO:
					pendente.add(tarefa);
					break;
				case PARAFAZER:
					fazer.add(tarefa);
					break;
				case EMPROGRESSO:
					progresso.add(tarefa);
					break;
				case CONCLUIDO:
					concluido.add(tarefa);
					break;
				}
			}
		}

		if ((pendente.size() > fazer.size()) || (pendente.size() > progresso.size())
				|| (pendente.size() > concluido.size()))
			maior = pendente.size();
		else if ((fazer.size() > progresso.size()) || (fazer.size() > concluido.size()))
			maior = fazer.size();
		else if (progresso.size() > concluido.size())
			maior = progresso.size();
		else
			maior = concluido.size();

		result.include("pendente", pendente);
		result.include("fazer", fazer);
		result.include("progresso", progresso);
		result.include("concluido", concluido);

		result.include("projeto", projeto);

		result.include("maior", maior);
		result.include("restantes", restantes);
		result.include("atrasados", atrasados);
		result.include("concluidos", concluidos);
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
	
	@Get("listar/colaborador")
	@SomenteLogado
	public void listarPorColaborador() {
		List<Projeto> projetos = projetoDAO.buscarPorColaborador(sessao.getUsuario());
		
		System.out.println("projetos "+projetos.size());
		
		result.include("projetos", projetos);
	}

	@Get("resumo/colaborador/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void resumoColaborador(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);

		long total = tarefaDAO.quantidadeDeTarefasPorProjeto(projeto);
		int atrasados = 0;
		int concluidos = 0;
		int restantes = 0;
		int fazer = 0;
		int progresso = 0;
		int concluido = 0;
		int dia = 0;
		int ociosos = 0;

		if (total > 0) {
			concluidos = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			restantes = 100 - concluidos;
			fazer = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.PARAFAZER);
			progresso = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.EMPROGRESSO);
			concluido = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			atrasados = resumoServico.percentualTarefasAtrasadasPorProjeto(projeto);
			dia = 100 - atrasados;
			ociosos = resumoServico.numeroColaboradoresOciososPorProjeto(projeto);
		}

		result.include("ociosos", ociosos);
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
		int ociosos = 0;

		if (total > 0) {
			concluidos = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			restantes = 100 - concluidos;
			fazer = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.PARAFAZER);
			progresso = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.EMPROGRESSO);
			concluido = resumoServico.percentualPorProjetoEEstado(projeto, EstadoTarefa.CONCLUIDO);
			atrasados = resumoServico.percentualTarefasAtrasadasPorProjeto(projeto);
			dia = 100 - atrasados;
			ociosos = resumoServico.numeroColaboradoresOciososPorProjeto(projeto);
		}

		result.include("ociosos", ociosos);
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
	public void cadastrarTarefa(Tarefa tarefa, String inicio) {
		result.forwardTo(TarefaController.class).cadastrar(tarefa, inicio);
	}

	@Get("tarefa/editar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void editarTarefa(Integer id) {
		result.forwardTo(TarefaController.class).editar(id);
	}

	@Post("tarefa/editar")
	@SomenteLogado
	public void editarTarefa(Tarefa tarefa, String inicio) {
		result.forwardTo(TarefaController.class).editar(tarefa, inicio);
	}

	@Get("tarefa/remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void removerTarefa(Integer id) {
		result.forwardTo(TarefaController.class).remover(id);
	}

	@Get("tarefa/esquerda/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void esquerdaTarefa(Integer id) {
		result.forwardTo(TarefaController.class).esquerda(id);
	}

	@Get("tarefa/direita/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void direitaTarefa(Integer id) {
		result.forwardTo(TarefaController.class).direita(id);
	}
	
	@Get("tarefa/colaborador/esquerda/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void esquerdaColaradorTarefa(Integer id) {
		result.forwardTo(TarefaController.class).esquerdaColaborador(id);
	}

	@Get("tarefa/colaborador/direita/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void direitaColaboradorTarefa(Integer id) {
		result.forwardTo(TarefaController.class).direitaColaborador(id);
	}

	@Get("tarefa/colaborador/add/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void addColaboradorTarefa(Integer id) {
		result.forwardTo(TarefaController.class).addColaborador(id);
	}

	@Get("tarefa/colaborador/alterar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void altColaboradorTarefa(Integer id) {
		result.forwardTo(TarefaController.class).altColaborador(id);
	}

	@Get("tarefa/visualizar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void visualizarTarefa(Integer id) {
		result.forwardTo(TarefaController.class).visualizar(id);
	}
	
	@Get("tarefa/pendente")
	@SomenteLogado
	public void tarefasPendente() {
		result.forwardTo(TarefaController.class).tarefasPendente();
	}

	@Get("tarefa/aceitar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void aceitar(Integer id) {
		result.forwardTo(TarefaController.class).aceitar(id);
	}

	@Get("tarefa/rejeitar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void rejeitar(Integer id) {
		result.forwardTo(TarefaController.class).rejeitar(id);
	}
}
