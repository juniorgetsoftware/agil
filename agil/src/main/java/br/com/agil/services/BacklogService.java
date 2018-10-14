package br.com.agil.services;

import java.util.List;

import br.com.agil.models.Backlog;
import br.com.agil.models.Produto;

public interface BacklogService extends Service<Backlog, Long> {

	List<Backlog> backlogsPorProduto(Produto produto);

}
