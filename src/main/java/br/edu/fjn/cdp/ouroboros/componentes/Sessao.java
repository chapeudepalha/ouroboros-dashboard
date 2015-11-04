package br.edu.fjn.cdp.ouroboros.componentes;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

@SessionScoped
@Named("sessao")
public class Sessao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	@Inject
	public Sessao() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void login(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void logout() {
		this.usuario = null;
	}
	
	public boolean usuarioEstaLogado() {
		boolean resultado = false;
		
		if (getUsuario() != null)
			resultado = true;
		else
			resultado = false;
		
		return resultado;
	}
	
}
