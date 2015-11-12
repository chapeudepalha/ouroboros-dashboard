package br.edu.fjn.cdp.ouroboros.controlador;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.componentes.Sessao;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;

@Controller
public class UsuarioController {

	@Inject
	private Result result;
	@Inject
	private Sessao sessao;
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	public UsuarioController() {

	}

	@Get("usuario/login")
	public void login() {
		
	}
	
	@Post("login")
	public void login(Usuario usuario) {
		Usuario u = null;
		
		u = usuarioDAO.buscarPorUsuarioESenha(usuario.getUsuario(), usuario.getSenha());
		
		if (u != null) {
			sessao.login(u);
			
			result.redirectTo(IndexController.class).inicio();
		} else {
			result.redirectTo(this).login();
		}
		
	}
	
	@Get("logout")
	public void logout() {
		sessao.logout();
		result.redirectTo(this).login();
	}

}
