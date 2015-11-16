package br.edu.fjn.cdp.ouroboros.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Equipe implements EntidadeOuroboros<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Usuario> colaboradores = new HashSet<Usuario>();

	public Equipe() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Usuario> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(Set<Usuario> colaboradores) {
		this.colaboradores = colaboradores;
	}
	
}
