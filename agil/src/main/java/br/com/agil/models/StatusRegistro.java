package br.com.agil.models;

public enum StatusRegistro {
	
	ATIVO ("Ativo", "fa-check", "color:#30ab2e"),
	INATIVO ("Inativo", "fa-ban", "color:#f77049");
	
	StatusRegistro(String descricao, String icone, String cor){
		this.descricao = descricao;
		this.icone = icone;
		this.cor = cor;
	}
	
	String descricao;
	String icone;
	String cor;
	
	public String getDescricao() {
		return descricao;
	}
	public String getIcone() {
		return icone;
	}
	public String getCor() {
		return cor;
	}
	
}
