package br.com.agil.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.agil.models.Projeto;
import br.com.agil.repository.ProjetoRepository;
import br.com.agil.services.ProjetoService;

public class ProjetoServiceImpl implements ProjetoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProjetoRepository projetoRepository;

	@Override
	public void salvar(Projeto projeto) {
		projetoRepository.save(projeto);
	}

	@Override
	public List<Projeto> listar() {
		return projetoRepository.findAll();
	}

	@Override
	public Projeto buscarPorId(Long id) {
		return projetoRepository.findBy(id);
	}

	@Override
	public void remover(Projeto projeto) {
		projetoRepository.remove(projeto);
	}

	@Override
	public void editar(Projeto projeto) {
		projetoRepository.save(projeto);
	}

	@Override
	public EntityRepository<Projeto, Long> getRepository() {
		return this.projetoRepository;
	}

	@Override
	public void alterarStatus(Projeto projeto) {
		projeto.alterarStatus();
		this.editar(projeto);
	}
}
