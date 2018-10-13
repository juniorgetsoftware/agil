package br.com.agil.models;

import static br.com.agil.models.StatusBacklog.NAO_ATRIBUIDO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
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

	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "sprint_id", nullable = false)
	private Sprint sprint;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Tarefa.class, mappedBy = "backlog", orphanRemoval = true)
	private List<Tarefa> tarefas;

	private int valorAgregado;

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

	public int getValorAgregado() {
		return valorAgregado;
	}

	public void setValorAgregado(int valorAgregado) {
		this.valorAgregado = valorAgregado;
	}

	public int getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(int tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}
}
