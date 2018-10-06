package br.com.agil.services;

import java.io.Serializable;
import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;

public interface Service  <T, PK extends Serializable> extends Serializable {
	
	void salvar(T t);

	void editar(T t);

	List<T> listar();

	T buscarPorId(Long id);

	void remover(T t);

	EntityRepository<T, PK> getRepository();

	void alterarStatus(T t);

}
