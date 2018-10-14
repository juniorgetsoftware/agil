package br.com.agil.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.agil.models.Backlog;
import br.com.agil.models.Produto;

@Repository
@ApplicationScoped
public interface BacklogRepository extends EntityRepository<Backlog, Long> {

	List<Backlog> findByProduto(Produto produto);

}
