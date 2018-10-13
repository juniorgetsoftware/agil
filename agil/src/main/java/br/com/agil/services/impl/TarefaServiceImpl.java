package br.com.agil.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.agil.models.Tarefa;
import br.com.agil.repository.TarefaRepository;
import br.com.agil.services.TarefaService;

public class TarefaServiceImpl implements TarefaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TarefaRepository tarefaRepository;

	@Override
	public void salvar(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}

	@Override
	public List<Tarefa> listar() {
		return tarefaRepository.findAll();
	}

	@Override
	public Tarefa buscarPorId(Long id) {
		return tarefaRepository.findBy(id);
	}

	@Override
	public void remover(Tarefa tarefa) {
		tarefaRepository.remove(tarefa);
	}

	@Override
	public void editar(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}

	@Override
	public EntityRepository<Tarefa, Long> getRepository() {
		return this.tarefaRepository;
	}

	@Override
	public void alterarStatus(Tarefa tarefa) {
		tarefa.alterarStatus();
		this.editar(tarefa);
	}
}
