package br.com.agil.models;

public enum StatusTarefa {

	A_FAZER ("A fazer"), 
	EM_ANDAMENTO("Em andamento"), 
	CONCLUIDO("Concluído");
	
	private StatusTarefa(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
