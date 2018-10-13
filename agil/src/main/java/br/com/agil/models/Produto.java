package br.com.agil.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "produto")
@Table(name = "produto")
public class Produto extends EntidadeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Produto() {
	}

	public Produto(String nome) {
		this.nome = nome;
	}

	@Size(min = 5, max = 255)
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	private String descricao;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Backlog.class,  
			mappedBy = "produto", orphanRemoval = true)
	private List<Backlog> itensBacklog;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Sprint.class,  
			mappedBy = "produto", orphanRemoval = true)
	private List<Sprint> sprints;
	
	@Transient//TODO implementar o uso de datas do java 8 
	private LocalDateTime dataHoraInicio = LocalDateTime.now();

	//
	
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

	public List<Backlog> getItensBacklog() {
		return itensBacklog;
	}

	public void setItensBacklog(List<Backlog> itensBacklog) {
		this.itensBacklog = itensBacklog;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

}
