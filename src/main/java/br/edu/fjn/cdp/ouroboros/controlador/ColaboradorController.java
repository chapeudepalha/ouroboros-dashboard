package br.edu.fjn.cdp.ouroboros.controlador;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.componentes.SomenteLogado;
import br.edu.fjn.cdp.ouroboros.modelo.Competencia;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;

@Controller
@Path("colaborador")
public class ColaboradorController {

	@Inject
	private Result result;
	@Inject
	private UsuarioDAO usuarioDAO;

	public ColaboradorController() {

	}

	@Get("novo")
	@SomenteLogado
	public void novo() {

	}

	@Post("cadastrar")
	@SomenteLogado
	public void cadastrar(Usuario usuario, Competencia competencia) {
		usuario.setTipoUsuario(TipoUsuario.COLABORADOR);
		usuario.getCompetencias().add(competencia);
		
		usuarioDAO.inserir(usuario);
		result.redirectTo(this).listar();
	}

	@Get("listar")
	@SomenteLogado
	public void listar() {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.COLABORADOR);

		result.include("usuarios", usuarios);
	}

	@Get("visualizar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void visualizar(Integer id) {
		Usuario usuario = usuarioDAO.buscarPorId(id);

		result.include("usuario", usuario);
	}

	@Get("editar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void editar(Integer id) {
		Usuario usuario = usuarioDAO.buscarPorId(id);

		result.include("usuario", usuario);
	}

	@Post("editar")
	@SomenteLogado
	public void editar(Usuario usuario) {
		usuario.setTipoUsuario(TipoUsuario.COLABORADOR);

		usuarioDAO.alterar(usuario);
		result.redirectTo(this).listar();
	}

	@Get("remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void remover(Integer id) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		usuarioDAO.remover(usuario);
		result.redirectTo(this).listar();
	}

}
