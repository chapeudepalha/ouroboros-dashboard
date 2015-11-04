package br.edu.fjn.cdp.ouroboros.controlador;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.UsuarioImplDAO;

@Controller
public class AdministradorController {

	@Inject
	private Result result;
	private UsuarioDAO usuarioDAO;
	
	public AdministradorController() {
		usuarioDAO = new UsuarioImplDAO();
	}

	@Get("index")
	public void inicio() {
		
	}
	
	@Get({"admin/novo", "adminstrado/novo"})
	public void novo() {
		
	}
	
	@Post({"admin/cadastrar", "adminstrado/cadastrar"})
	public void cadastrar(Usuario usuario) {
		usuarioDAO.inserir(usuario);
		result.redirectTo(InicioController.class).inicio();
	}

	@Get({"admin/listar", "adminstrado/listar"})
	public void listar() {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.ADMINISTRADOR);
		
		result.include("usuarios", usuarios);
	}
}