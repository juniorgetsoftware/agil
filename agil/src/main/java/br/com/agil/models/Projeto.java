package br.com.agil.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "projeto")
@Table(name = "projeto")
public class Projeto extends EntidadeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Projeto() {
	}

	public Projeto(String nome) {
		this.nome = nome;
	}

	@Size(min = 5, max = 255)
	@NotBlank
	@Column(nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
