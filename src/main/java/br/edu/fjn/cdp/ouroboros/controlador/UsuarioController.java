package br.edu.fjn.cdp.ouroboros.controlador;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.componentes.Sessao;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.UsuarioImplDAO;

@Controller
public class UsuarioController {

	@Inject
	private Result result;
	@Inject
	private Sessao sessao;
	private UsuarioDAO usuarioDAO;
	
	@Inject
	public UsuarioController() {
		usuarioDAO = new UsuarioImplDAO();
	}

	@Post("login")
	public void login(Usuario usuario) {
		Usuario u = null;
		
		u = usuarioDAO.buscarPorUsuarioESenha(usuario.getUsuario(), usuario.getSenha());
		
		if (u != null) {
			sessao.login(u);
			
			result.redirectTo(InicioController.class).inicio();
		} else {
			result.redirectTo(IndexController.class).inicio();
		}
		
	}
	
	@Get("logout")
	public void logout() {
		sessao.logout();
		result.redirectTo(IndexController.class).inicio();
	}

}
