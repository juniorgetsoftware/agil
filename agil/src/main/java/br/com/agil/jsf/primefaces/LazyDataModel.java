package br.com.agil.jsf.primefaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.deltaspike.data.api.EntityRepository;
import org.primefaces.model.SortOrder;

public class LazyDataModel<T, PK extends Serializable> extends org.primefaces.model.LazyDataModel<T>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntityRepository<T, PK> entityRepository;

	public LazyDataModel(EntityRepository<T, PK> entityRepository) {
		this.entityRepository = entityRepository;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		setRowCount(entityRepository.count().intValue());
		return entityRepository.findAll(first, pageSize);
	}

}
