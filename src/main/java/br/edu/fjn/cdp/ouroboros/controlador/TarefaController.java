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
		tarefa.setEstadoTarefa(EstadoTarefa.PARAFAZER);

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
	
	@Get("colaborador/{id:[0-9]{1,15}}")
	public void colaborador(Integer id) {
		Tarefa tarefa = tarefaDAO.buscarPorId(id);
		List<Usuario> colaboradores = usuarioDAO.buscarPorTipoUsuarioECompetencia(TipoUsuario.COLABORADOR, tarefa.getCompetencia());
		
		result.include("tarefa", tarefa);
		result.include("colaboradores", colaboradores);
	}

}
