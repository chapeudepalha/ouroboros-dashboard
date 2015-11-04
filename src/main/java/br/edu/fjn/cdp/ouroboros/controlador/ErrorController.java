package br.edu.fjn.cdp.ouroboros.controlador;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;

@Controller
public class ErrorController {

	public ErrorController() {
		// TODO Auto-generated constructor stub
	}
	
	@Get("/404")
	public void http404() {
		
	}
	
}