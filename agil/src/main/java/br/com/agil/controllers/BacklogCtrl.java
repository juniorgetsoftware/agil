package br.com.agil.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.omnifaces.cdi.Param;

import br.com.agil.controllers.converters.BacklogConverter;
import br.com.agil.jsf.FacesUtil;
import br.com.agil.jsf.Msgs;
import br.com.agil.jsf.primefaces.LazyDataModel;
import br.com.agil.models.Backlog;
import br.com.agil.models.ROI;
import br.com.agil.models.StatusBacklog;
import br.com.agil.services.BacklogService;

@Named
@ViewScoped
public class BacklogCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Param(name = "backlog", converterClass = BacklogConverter.class)
	private Backlog backlog;

	private LazyDataModel<Backlog, Long> backlogs;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private BacklogService backlogService;

	@Inject
	private FacesUtil facesUtil;

	public String salvar() {
		backlogService.salvar(backlog);
		msgs.addInfo().cadastradoComSucesso();
		backlog = null;
		facesUtil.atualizarComponente("msgs");
		return null;
	}

	public void alterarStatus() {
		backlogService.alterarStatus(backlog);
		msgs.addInfo().statusAlteradoSucesso();
		facesUtil.atualizarComponente("msgs");
	}
	
	public String editar() {
		backlogService.editar(backlog);
		msgs.addInfo().editadoComSucesso();
		backlog = null;
		facesUtil.atualizarComponente("msgs");
		return "/private/backlog/list.xhtml?faces-redirect=true";
	}

	public void listar() {
		if (facesUtil.isNotPostback() || isNull(backlogs)) {
			this.backlogs = new LazyDataModel<>(backlogService.getRepository());
		}
	}
	
	public StatusBacklog[] listarStatusBacklog() {
		return StatusBacklog.values();
	}

	public ROI[] listarROI() {
		return ROI.values();
	}

	public Backlog getBacklog() {
		if (isNull(backlog))
			backlog = new Backlog();
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public LazyDataModel<Backlog, Long> getBacklogs() {
		return backlogs;
	}

	public void setBacklogs(LazyDataModel<Backlog, Long> backlogs) {
		this.backlogs = backlogs;
	}

	public boolean isEditando() {
		return nonNull(getBacklog()) && nonNull(getBacklog().getId());
	}

}
