package br.com.agil.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "tarefa")
@Table(name = "tarefa")
public class Tarefa extends EntidadeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tarefa() {
	}

	public Tarefa(String nome) {
		this.nome = nome;
	}

	@Size(min = 5, max = 255)
	@NotBlank
	@Column(nullable = false)
	private String nome;
	private String descricao;

	@Enumerated(EnumType.STRING)
	private StatusTarefa statusTarefa;

	@ManyToOne(targetEntity = Backlog.class)
	@JoinColumn(name = "backlog_id", nullable = false)
	private Backlog backlog;

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

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
}
