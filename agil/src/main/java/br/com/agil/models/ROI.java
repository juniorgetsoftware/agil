package br.com.agil.models;

public enum ROI {

	BAIXISSIMO("Baixíssimo"),
	BAIXO("Baixo"),
	MEDIO("Médio"),
	ALTO("Alto"),
	ALTISSIMO("Altíssimo");
	
	ROI(String descricao){
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
}
