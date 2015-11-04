package br.edu.fjn.cdp.ouroboros.modelo;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarefa implements EntidadeOuroboros<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Temporal(TemporalType.DATE)
	@Column
	private Calendar inicio;
	@Temporal(TemporalType.DATE)
	@Column
	private Calendar fim;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "id_colaborador")
	private Usuario colaboradorResponsavel;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "estado_atividade")
	private EstadoTarefa estadoTarefa;
	
	public Tarefa() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Calendar getFim() {
		return fim;
	}

	public void setFim(Calendar fim) {
		this.fim = fim;
	}

	public Usuario getColaboradorResponsavel() {
		return colaboradorResponsavel;
	}

	public void setResponsavel(Usuario colaboradorResponsavel) {
		this.colaboradorResponsavel = colaboradorResponsavel;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public EstadoTarefa getEstadoTarefa() {
		return estadoTarefa;
	}

	public void setEstadoTarefa(EstadoTarefa estadoTarefa) {
		this.estadoTarefa = estadoTarefa;
	}

	public void setColaboradorResponsavel(Usuario colaboradorResponsavel) {
		this.colaboradorResponsavel = colaboradorResponsavel;
	}

}