package br.edu.fjn.cdp.ouroboros.modelo;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "projeto")
public class Projeto implements EntidadeOuroboros<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente")
	private Usuario cliente;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_equipe")
	private Equipe equipe = new Equipe();
	@Column
	private String nome;
	@Column
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Calendar inicio;
	@Temporal(TemporalType.DATE)
	private Calendar prazoPrevisto;
	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<Integer> semana;
	private Double comeco;
	private Double fim;

	public Projeto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public Calendar getPrazoPrevisto() {
		return prazoPrevisto;
	}

	public void setPrazoPrevisto(Calendar prazoPrevisto) {
		this.prazoPrevisto = prazoPrevisto;
	}

	public Collection<Integer> getSemana() {
		return semana;
	}

	public void setSemana(Collection<Integer> semana) {
		this.semana = semana;
	}

	public Double getComeco() {
		return comeco;
	}

	public void setComeco(Double comeco) {
		this.comeco = comeco;
	}

	public Double getFim() {
		return fim;
	}

	public void setFim(Double fim) {
		this.fim = fim;
	}

}
