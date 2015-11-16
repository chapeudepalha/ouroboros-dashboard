package br.edu.fjn.cdp.ouroboros.modelo.dao;

import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.Competencia;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

public interface UsuarioDAO extends DAOGenerico<Usuario, Integer> {

	Usuario buscarPorUsuarioESenha(String usuario, String senha);
	
	Usuario buscarPorUsuario(String usuario);
	
	List<Usuario> buscarPorTipoUsuario(TipoUsuario tipoUsuario);
	
	List<Usuario> buscarPorTipoUsuarioECompetencia(TipoUsuario tipoUsuario, Competencia competencia);
	
	void adicionarCompetencia(Usuario usuario, Competencia competencia);
	
	void removerCompetencia(Usuario usuario, Competencia competencia);
}
