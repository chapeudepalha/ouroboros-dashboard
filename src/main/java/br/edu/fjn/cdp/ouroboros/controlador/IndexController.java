package br.edu.fjn.cdp.ouroboros.controlador;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;

@Controller
public class IndexController {

	public IndexController() {
	}

	@Get("/")
	public void index() {
		
	}

	@Get("inicio")
	public void inicio() {
		
		/* if (sessao.getUsuario().getTipoUsuario() == TipoUsuario.ROOT) {
			result.forwardTo(RootController.class).inicio();
		} else if (sessao.getUsuario().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
			result.forwardTo(AdministradorController.class).inicio();
		} else if (sessao.getUsuario().getTipoUsuario() == TipoUsuario.COLABORADOR) {
			result.forwardTo(ColaboradorController.class).inicio();
		} else if (sessao.getUsuario().getTipoUsuario() == TipoUsuario.CLIENTE) {
			result.forwardTo(ColaboradorController.class).inicio();
		} else {
			result.redirectTo(IndexController.class).inicio();
		} */
		
	}
}
