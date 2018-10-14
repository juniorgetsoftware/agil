package br.com.agil.models;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "sprint")
@Table(name = "sprint")
public class Sprint extends EntidadeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sprint() {
	}

	public Sprint(String nome) {
		this.nome = nome;
	}

	@Size(min = 5, max = 255)
	@NotBlank
	@Column(nullable = false)
	private String nome;

	@ManyToMany(targetEntity = Backlog.class)
	@JoinTable(name = "sprint_backlog", joinColumns = { @JoinColumn(name = "sprint_id") }, inverseJoinColumns = {
			@JoinColumn(name = "backlog_id") })
	private List<Backlog> backlogs;

	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@Column(name = "data_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTempoInicio;

	@Column(name = "data_termino")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTempoTermino;

	//

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Backlog> getBacklogs() {
		if (isNull(backlogs))
			backlogs = new ArrayList<>();
		return backlogs;
	}

	public void setBacklogs(List<Backlog> backlogs) {
		this.backlogs = backlogs;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDataTempoInicio() {
		return dataTempoInicio;
	}

	public void setDataTempoInicio(Date dataTempoInicio) {
		this.dataTempoInicio = dataTempoInicio;
	}

	public Date getDataTempoTermino() {
		return dataTempoTermino;
	}

	public void setDataTempoTermino(Date dataTempoTermino) {
		this.dataTempoTermino = dataTempoTermino;
	}

}
