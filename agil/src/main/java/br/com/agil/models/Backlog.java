package br.com.agil.models;

import static br.com.agil.models.ROI.MEDIO;
import static br.com.agil.models.StatusBacklog.NAO_ATRIBUIDO;
import static java.util.Objects.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "backlog")
@Table(name = "backlog")
public class Backlog extends EntidadeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Backlog() {
	}

	public Backlog(String titulo) {
		this.titulo = titulo;
	}

	@Size(min = 5, max = 255)
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	private String descricao;

	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@ManyToOne(targetEntity = Sprint.class)
	@JoinTable(name = "sprint_backlog", joinColumns = { @JoinColumn(name = "sprint_id") }, inverseJoinColumns = {
			@JoinColumn(name = "backlog_id") })
	private Sprint sprint;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Tarefa.class, mappedBy = "backlog", orphanRemoval = true)
	private List<Tarefa> tarefas;

	@Enumerated(EnumType.STRING)
	private ROI roi = MEDIO;

	@Column(name = "tempo_estimado")
	private int tempoEstimado;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_backlog")
	private StatusBacklog statusBacklog = NAO_ATRIBUIDO;

	//

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Tarefa> getTarefas() {
		if (isNull(tarefas))
			tarefas = new ArrayList<>();
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public ROI getRoi() {
		return roi;
	}

	public void setRoi(ROI roi) {
		this.roi = roi;
	}

	public int getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(int tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public StatusBacklog getStatusBacklog() {
		return statusBacklog;
	}

	public void setStatusBacklog(StatusBacklog statusBacklog) {
		this.statusBacklog = statusBacklog;
	}

	public void adicionar(Tarefa tarefa) {
		if (isNull(tarefa))
			throw new RuntimeException("Tarefa não pode ser nulo");
		tarefa.setBacklog(this);
		this.getTarefas().add(tarefa);
		tarefa = null;
	}

	public void atualizar(Tarefa tarefa) {
		if (isNull(tarefa))
			throw new RuntimeException("Tarefa não pode ser nulo");
		tarefa.setBacklog(this);
		int index = this.getTarefas().indexOf(tarefa);
		this.getTarefas().set(index, tarefa);
		tarefa = null;
	}

	public void remover(Tarefa tarefa) {
		if (isNull(tarefa))
			throw new RuntimeException("Tarefa não pode ser nulo");
		this.getTarefas().remove(tarefa);
		tarefa.setBacklog(null);
		tarefa = null;
	}

}
