package br.com.agil.repository;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.agil.models.Projeto;

@Repository
@ApplicationScoped
public interface ProjetoRepository extends EntityRepository<Projeto, Long> {

}
