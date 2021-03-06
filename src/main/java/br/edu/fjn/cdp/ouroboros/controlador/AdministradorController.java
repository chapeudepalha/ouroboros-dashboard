package br.edu.fjn.cdp.ouroboros.controlador;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fjn.cdp.ouroboros.componentes.SomenteLogado;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;

@Controller
@Path("admin")
public class AdministradorController {

	@Inject
	private Result result;
	@Inject
	private UsuarioDAO usuarioDAO;

	public AdministradorController() {

	}

	@Get("novo")
	@SomenteLogado
	public void novo() {

	}

	@Post("cadastrar")
	@SomenteLogado
	public void cadastrar(Usuario usuario) {
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);

		usuarioDAO.inserir(usuario);
		result.redirectTo(this).listar();
	}

	@Get("listar")
	@SomenteLogado
	public void listar() {
		List<Usuario> usuarios = usuarioDAO.buscarPorTipoUsuario(TipoUsuario.ADMINISTRADOR);

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
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);

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