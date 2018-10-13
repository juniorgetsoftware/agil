package br.com.agil.repository;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.agil.models.Produto;

@Repository
@ApplicationScoped
public interface ProdutoRepository extends EntityRepository<Produto, Long> {

}
