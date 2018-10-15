package br.com.agil.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.agil.models.Backlog;
import br.com.agil.models.Produto;
import br.com.agil.repository.BacklogRepository;
import br.com.agil.services.BacklogService;

public class BacklogServiceImpl implements BacklogService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private BacklogRepository backlogRepository;

	@Override
	public void salvar(Backlog backlog) {
		backlogRepository.save(backlog);
	}

	@Override
	public List<Backlog> listar() {
		return backlogRepository.findAll();
	}

	@Override
	public Backlog buscarPorId(Long id) {
		return backlogRepository.findBy(id);
	}

	@Override
	public void remover(Backlog backlog) {
		backlogRepository.remove(backlog);
	}

	@Override
	public void editar(Backlog backlog) {
		backlogRepository.save(backlog);
	}

	@Override
	public EntityRepository<Backlog, Long> getRepository() {
		return this.backlogRepository;
	}

	@Override
	public void alterarStatus(Backlog backlog) {
		backlog.alterarStatus();
		this.editar(backlog);
	}

	@Override
	public void statusAFazer(Backlog backlog) {
		backlog.statusAFazer();
		this.editar(backlog);
	}

	@Override
	public void statusCancelar(Backlog backlog) {
		backlog.statusCancelar();
		this.editar(backlog);
	}

	@Override
	public void statusConcluido(Backlog backlog) {
		backlog.statusConcluido();
		this.editar(backlog);
	}

	@Override
	public void statusEmAndamento(Backlog backlog) {
		backlog.statusEmAndamento();
		this.editar(backlog);
	}

	@Override
	public void statusNaoAtribuido(Backlog backlog) {
		backlog.statusNaoAtribuido();
		this.editar(backlog);
	}

	@Override
	public List<Backlog> backlogsPorProduto(Produto produto) {
		return backlogRepository.findByProduto(produto);
	}
}
