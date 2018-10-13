package br.com.agil.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.omnifaces.cdi.Param;

import br.com.agil.controllers.converters.SprintConverter;
import br.com.agil.jsf.FacesUtil;
import br.com.agil.jsf.Msgs;
import br.com.agil.jsf.primefaces.LazyDataModel;
import br.com.agil.models.Sprint;
import br.com.agil.services.SprintService;

@Named
@ViewScoped
public class SprintCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Param(name = "sprint", converterClass = SprintConverter.class)
	private Sprint sprint;

	private LazyDataModel<Sprint, Long> sprints;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private SprintService sprintService;

	@Inject
	private FacesUtil facesUtil;

	public String salvar() {
		sprintService.salvar(sprint);
		msgs.addInfo().cadastradoComSucesso();
		sprint = null;
		facesUtil.atualizarComponente("msgs");
		return null;
	}

	public void alterarStatus() {
		sprintService.alterarStatus(sprint);
		msgs.addInfo().statusAlteradoSucesso();
		facesUtil.atualizarComponente("msgs");
	}
	
	public String editar() {
		sprintService.editar(sprint);
		msgs.addInfo().editadoComSucesso();
		sprint = null;
		facesUtil.atualizarComponente("msgs");
		return "/private/sprint/list.xhtml?faces-redirect=true";
	}

	public void listar() {
		if (facesUtil.isNotPostback() || isNull(sprints)) {
			this.sprints = new LazyDataModel<>(sprintService.getRepository());
		}
	}

	public Sprint getSprint() {
		if (isNull(sprint))
			sprint = new Sprint();
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public LazyDataModel<Sprint, Long> getSprints() {
		return sprints;
	}

	public void setSprints(LazyDataModel<Sprint, Long> sprints) {
		this.sprints = sprints;
	}

	public boolean isEditando() {
		return nonNull(getSprint()) && nonNull(getSprint().getId());
	}

}
