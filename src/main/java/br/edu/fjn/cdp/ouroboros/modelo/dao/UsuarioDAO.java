package br.edu.fjn.cdp.ouroboros.modelo.dao;

import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

public interface UsuarioDAO extends DAOGenerico<Usuario, Integer> {

	Usuario buscarPorUsuarioESenha(String usuario, String senha);
	
	List<Usuario> buscarPorTipoUsuario(TipoUsuario tipoUsuario);
	
}
