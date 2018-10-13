package br.com.agil.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Backlog.class)
	private List<Backlog> backlogs;

	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@Transient // TODO implementar o uso de datas do java 8
	private LocalDateTime dataTempoInicio;

	@Transient // TODO implementar o uso de datas do java 8
	private LocalDateTime dataTempoTermino;

	//

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Backlog> getBacklogs() {
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

	public LocalDateTime getDataTempoInicio() {
		return dataTempoInicio;
	}

	public void setDataTempoInicio(LocalDateTime dataTempoInicio) {
		this.dataTempoInicio = dataTempoInicio;
	}

	public LocalDateTime getDataTempoTermino() {
		return dataTempoTermino;
	}

	public void setDataTempoTermino(LocalDateTime dataTempoTermino) {
		this.dataTempoTermino = dataTempoTermino;
	}

}
