package br.edu.fjn.cdp.ouroboros.controlador;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.edu.fjn.cdp.ouroboros.componentes.SomenteLogado;

@Controller
public class IndexController {

	public IndexController() {
		
	}
	
	@Get("/")
	@SomenteLogado
	public void inicio() {

	}
	
}
