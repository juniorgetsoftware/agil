package br.com.agil.repository;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.agil.models.Backlog;

@Repository
@ApplicationScoped
public interface BacklogRepository extends EntityRepository<Backlog, Long> {

}
