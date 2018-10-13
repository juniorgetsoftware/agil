package br.com.agil.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.agil.models.Produto;
import br.com.agil.repository.ProdutoRepository;
import br.com.agil.services.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository produtoRepository;

	@Override
	public void salvar(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto buscarPorId(Long id) {
		return produtoRepository.findBy(id);
	}

	@Override
	public void remover(Produto produto) {
		produtoRepository.remove(produto);
	}

	@Override
	public void editar(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public EntityRepository<Produto, Long> getRepository() {
		return this.produtoRepository;
	}

	@Override
	public void alterarStatus(Produto produto) {
		produto.alterarStatus();
		this.editar(produto);
	}
}
