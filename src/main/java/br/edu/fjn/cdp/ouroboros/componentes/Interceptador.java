package br.edu.fjn.cdp.ouroboros.componentes;

import javax.inject.Inject;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.edu.fjn.cdp.ouroboros.controlador.UsuarioController;

@Intercepts
public class Interceptador implements Interceptor {

	@Inject
	private Sessao sessao;
	@Inject
	private Result result;

	@Override
	public boolean accepts(ControllerMethod method) {
		return method.getMethod().isAnnotationPresent(SomenteLogado.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object resourceInstance) {
		if (sessao.usuarioEstaLogado()) {
            stack.next(method, resourceInstance);
        } else {
            result.redirectTo(UsuarioController.class).login();
        }
	}

}
