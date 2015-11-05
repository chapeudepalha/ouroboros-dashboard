package br.edu.fjn.cdp.ouroboros.controlador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.modelo.Equipe;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.EquipeDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.ProjetoDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.EquipeImplDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.ProjetoImplDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.UsuarioImplDAO;

@Controller
@Path("projeto")
public class ProjetoController {

	@Inject
	private Result result;
	private ProjetoDAO projetoDAO;
	private UsuarioDAO usuarioDAO;
	private EquipeDAO equipeDAO;

	public ProjetoController() {
		projetoDAO = new ProjetoImplDAO();
		usuarioDAO = new UsuarioImplDAO();
		equipeDAO = new EquipeImplDAO();
	}

	@Get("novo")
	public void novo() {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.CLIENTE);

		result.include("usuarios", usuarios);
	}

	@Post("cadastrar")
	public void cadastrar(Projeto projeto) {
		projetoDAO.inserir(projeto);
		result.redirectTo(this).listar();
	}

	@Get("editar/{id:[0-9]{1,15}}")
	public void editar(Integer id) {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.CLIENTE);
		result.include("usuarios", usuarios);

		Projeto projeto = projetoDAO.buscarPorId(id);
		result.include("projeto", projeto);
	}

	@Post("editar")
	public void editar(Projeto projeto) {
		projetoDAO.alterar(projeto);
		result.redirectTo(this).listar();
	}

	@Get("colaboradores/{id:[0-9]{1,15}}")
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
	public void addColaborador(Usuario usuario, Projeto projeto) {
		Projeto proj = projetoDAO.buscarPorId(projeto.getId());
		Usuario u = usuarioDAO.buscarPorId(usuario.getId());

		if (proj.getEquipe() == null)
			proj.setEquipe(new Equipe());
		
		equipeDAO.adicionarColaborador(proj.getEquipe(), u);

		result.redirectTo(this).colaboradores(proj.getId());
	}

	@Get("colaborador/remover/{idColaborador:[0-9]{1,15}}/{idProjeto:[0-9]{1,15}}")
	public void removerColaborador(Integer idColaborador, Integer idProjeto) {
		Projeto p = projetoDAO.buscarPorId(idProjeto);
		Usuario u = usuarioDAO.buscarPorId(idColaborador);

		Equipe e = p.getEquipe();
		
		equipeDAO.removerColaborador(e, u);

		result.redirectTo(this).colaboradores(p.getId());
	}

	@Get("visualizar/{id:[0-9]{1,15}}")
	public void visualizar(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);
		result.include("projeto", projeto);
	}

	@Get("remover/{id:[0-9]{1,15}}")
	public void remover(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);
		projetoDAO.remover(projeto);

		result.redirectTo(this).listar();
	}

	@Get("listar")
	public void listar() {
		List<Projeto> projetos = projetoDAO.listar();

		result.include("projetos", projetos);
	}
}
