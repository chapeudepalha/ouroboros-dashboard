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
import br.edu.fjn.cdp.ouroboros.modelo.dao.CompetenciaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;

@Controller
@Path("colaborador")
public class ColaboradorController {

	@Inject
	private Result result;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private CompetenciaDAO competenciaDAO;

	public ColaboradorController() {

	}

	@Get("novo")
	@SomenteLogado
	public void novo() {
		List<Competencia> competencias = competenciaDAO.listar();
		result.include("competencias", competencias);
	}

	@Post("cadastrar")
	@SomenteLogado
	public void cadastrar(Usuario usuario) {
		usuario.setTipoUsuario(TipoUsuario.COLABORADOR);

		usuarioDAO.inserir(usuario);
		
		usuario = usuarioDAO.buscarPorUsuarioESenha(usuario.getUsuario(), usuario.getSenha());	
		result.redirectTo(this).competencias(usuario.getId());
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
		List<Competencia> competencias = competenciaDAO.listar();

		result.include("competencias", competencias);
		result.include("usuario", usuario);
	}

	@Post("editar")
	@SomenteLogado
	public void editar(Usuario usuario, Competencia competencia) {
		usuario.setTipoUsuario(TipoUsuario.COLABORADOR);
		usuario.getCompetencias().add(competencia);

		usuarioDAO.alterar(usuario);
		result.redirectTo(this).listar();
	}

	@Get("competencias/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void competencias(Integer id) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		List<Competencia> competencias = competenciaDAO.listar();

		result.include("competencias", competencias);
		result.include("usuario", usuario);
	}
	
	@Post("competencia/add")
	@SomenteLogado
	public void competencias(Usuario usuario, Competencia competencia) {
		usuarioDAO.adicionarCompetencia(usuario, competencia);
		
		result.redirectTo(this).competencias(usuario.getId());
	}

	@Get("competencia/remover/{idColaborador:[0-9]{1,15}}/{idCompetencia:[0-9]{1,15}}")
	@SomenteLogado
	public void removerCompetencia(Integer idColaborador, Integer idCompetencia) {
		Usuario usuario = usuarioDAO.buscarPorId(idColaborador);
		Competencia competencia = competenciaDAO.buscarPorId(idCompetencia);
		
		usuarioDAO.removerCompetencia(usuario, competencia);
		result.redirectTo(this).competencias(idColaborador);
	}
	
	@Get("remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void remover(Integer id) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		usuarioDAO.remover(usuario);
		result.redirectTo(this).listar();
	}

}
