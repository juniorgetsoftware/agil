package br.com.agil.repository;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.agil.models.Sprint;

@Repository
@ApplicationScoped
public interface SprintRepository extends EntityRepository<Sprint, Long> {

}
