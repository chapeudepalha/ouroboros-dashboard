package br.edu.fjn.cdp.ouroboros.controlador;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.ProjetoDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.ProjetoImplDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.UsuarioImplDAO;

@Controller
@Path("projeto")
public class ProjetoController {

	@Inject
	private Result result;
	private ProjetoDAO projetoDAO;
	private UsuarioDAO usuarioDAO;

	public ProjetoController() {
		projetoDAO = new ProjetoImplDAO();
		usuarioDAO = new UsuarioImplDAO();
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
	
	@Get("add/{id:[0-9]{1,15}}")
	public void add(Integer id) {
		Projeto projeto = projetoDAO.buscarPorId(id);
		result.include("projeto", projeto);
	}
	
	@Post("add")
	public void add(Projeto projeto) {
		projetoDAO.alterar(projeto);
		result.redirectTo(this).add(projeto.getId());
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
