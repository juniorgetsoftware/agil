package br.com.agil.models;

public enum StatusBacklog {
	
	CANCELADO("Cancelado"), 
	A_FAZER ("A fazer"), 
	EM_ANDAMENTO("Em andamento"), 
	CONCLUIDO("Concluído"), 
	NAO_ATRIBUIDO("Não atribuído");
	
	private StatusBacklog(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

}
