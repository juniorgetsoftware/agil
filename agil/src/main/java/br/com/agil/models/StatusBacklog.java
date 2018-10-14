package br.com.agil.models;

public enum StatusBacklog {
	
	NAO_ATRIBUIDO("Não atribuído"),
	A_FAZER ("A fazer"), 
	EM_ANDAMENTO("Em andamento"), 
	CONCLUIDO("Concluído"), 
	CANCELADO("Cancelado"); 
	
	private StatusBacklog(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

}
