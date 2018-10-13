package br.com.agil.models;

public enum StatusSprint {

	INICIADA ("Iniciada"), 
	EM_ANDAMENTO("Em andamento"), 
	CANCELADA("Cancelada"), 
	CONCLUIDA("Concluída");
	
	private StatusSprint(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
