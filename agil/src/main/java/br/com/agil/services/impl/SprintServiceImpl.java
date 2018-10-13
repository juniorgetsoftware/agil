package br.com.agil.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.agil.models.Sprint;
import br.com.agil.repository.SprintRepository;
import br.com.agil.services.SprintService;

public class SprintServiceImpl implements SprintService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private SprintRepository sprintRepository;

	@Override
	public void salvar(Sprint sprint) {
		sprintRepository.save(sprint);
	}

	@Override
	public List<Sprint> listar() {
		return sprintRepository.findAll();
	}

	@Override
	public Sprint buscarPorId(Long id) {
		return sprintRepository.findBy(id);
	}

	@Override
	public void remover(Sprint sprint) {
		sprintRepository.remove(sprint);
	}

	@Override
	public void editar(Sprint sprint) {
		sprintRepository.save(sprint);
	}

	@Override
	public EntityRepository<Sprint, Long> getRepository() {
		return this.sprintRepository;
	}

	@Override
	public void alterarStatus(Sprint sprint) {
		sprint.alterarStatus();
		this.editar(sprint);
	}
}
