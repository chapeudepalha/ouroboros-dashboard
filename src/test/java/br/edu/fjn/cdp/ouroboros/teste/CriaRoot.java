package br.edu.fjn.cdp.ouroboros.teste;

import java.util.Calendar;

import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.ProjetoDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.ProjetoImplDAO;
import br.edu.fjn.cdp.ouroboros.modelo.dao.impl.UsuarioImplDAO;
import br.edu.fjn.cdp.ouroboros.servico.ResumoServico;
import br.edu.fjn.cdp.ouroboros.servico.impl.ResumoServicoImpl;

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
	
	public static void mainf(String[] args) {
		System.out.println("Domingo " + Calendar.SUNDAY);
		System.out.println("Segunda " + Calendar.MONDAY);
		System.out.println("Terça " + Calendar.TUESDAY);
		System.out.println("Quarta " + Calendar.WEDNESDAY);
		System.out.println("Quinta " + Calendar.THURSDAY);
		System.out.println("Sexta " + Calendar.FRIDAY);
		System.out.println("Sábado " + Calendar.SATURDAY);
	}
	
	public static void maine(String[] args) {
		ResumoServico ser = new ResumoServicoImpl();
		ProjetoDAO pdao = new ProjetoImplDAO();
		
		Projeto p = pdao.buscarPorId(1);
		
		int s = ser.buscarTarefasAtrasadasPorProjeto(p).size();
		System.out.println("tarefas atrasadas "+s);
	}
}
