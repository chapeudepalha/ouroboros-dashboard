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
import br.edu.fjn.cdp.ouroboros.modelo.dao.CompetenciaDAO;

@Controller
@Path("competencia")
public class CompetenciaController {

	@Inject
	private CompetenciaDAO competenciaDAO;
	@Inject
	private Result result;
	
	public CompetenciaController() {
		// TODO Auto-generated constructor stub
	}
	
	@Get("novo")
	@SomenteLogado
	public void novo() {
		
	}
	
	@Post("cadastrar")
	@SomenteLogado
	public void cadastrar(Competencia competencia) {
		competenciaDAO.inserir(competencia);
		
		result.redirectTo(this).listar();
	}
	
	@Get("editar/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void editar(Integer id) {
		Competencia competencia = competenciaDAO.buscarPorId(id);
		
		result.include("competencia", competencia);
	}
	
	@Post("editar")
	@SomenteLogado
	public void editar(Competencia competencia) {
		competenciaDAO.alterar(competencia);
		
		result.redirectTo(this).listar();
	}
	
	@Get("remover/{id:[0-9]{1,15}}")
	@SomenteLogado
	public void remover(Integer id) {
		Competencia competencia = competenciaDAO.buscarPorId(id);
		
		competenciaDAO.remover(competencia);
		result.redirectTo(this).listar();
	}
	
	@Get("listar")
	@SomenteLogado
	public void listar() {
		List<Competencia> competencias = competenciaDAO.listar();
		
		result.include("competencias", competencias);
	}
}
