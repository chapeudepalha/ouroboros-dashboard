package br.edu.fjn.cdp.ouroboros.teste;

import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.UsuarioImplDAO;

public class CriaRoot {

	public static void maind(String[] args) {
		
		Usuario u = new Usuario();
		u.setPessoa(null);
		u.setUsuario("root");
		u.setSenha("root");
		u.setTipoUsuario(TipoUsuario.ROOT);
		
		UsuarioDAO dao = new UsuarioImplDAO();
		dao.inserir(u);
		
		/*
		Usuario u = new Usuario();
		
		UsuarioDAO dao = new UsuarioImplDAO();
		
		u = dao.buscarPorUsuarioESenha("root", "root");
		System.out.println("usuario " + u.getUsuario());
		System.out.println("tipo " + u.getTipoUsuario());*/
	}
	
}
