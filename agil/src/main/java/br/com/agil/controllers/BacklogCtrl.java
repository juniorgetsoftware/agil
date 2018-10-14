package br.com.agil.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.List;

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
import br.com.agil.models.Produto;
import br.com.agil.models.ROI;
import br.com.agil.models.StatusBacklog;
import br.com.agil.models.Tarefa;
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
	private List<Backlog> backlogList;
	private Produto produto;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private BacklogService backlogService;

	@Inject
	private FacesUtil facesUtil;

	private Tarefa tarefa;

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

	public void listarSemDemanda() {
		if (facesUtil.isNotPostback() || isNull(backlogList)) {
			this.backlogList = backlogService.listar();
		}
	}

	public void listarBacklogsPorProduto() {
		if (facesUtil.isNotPostback() || isNull(backlogList)) {
			this.backlogList = backlogService.backlogsPorProduto(produto);
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

	public Tarefa getTarefa() {
		if (isNull(tarefa))
			tarefa = new Tarefa();
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void adicionar() {
		this.backlog.adicionar(tarefa);
		msgs.addInfo().tarefaCadastradaComSucesso();
		facesUtil.atualizarComponente("msgs-tarefa");
		tarefa = new Tarefa();
	}

	public void atualizar() {
		this.backlog.atualizar(tarefa);
		msgs.addInfo().tarefaEditadaComSucesso();
		facesUtil.atualizarComponente("msgs-tarefa");
		tarefa = new Tarefa();
	}

	public void remover() {
		this.backlog.remover(tarefa);
		msgs.addInfo().tarefaDeletadaComSucesso();
		facesUtil.atualizarComponente("msgs-tarefa");
		tarefa = new Tarefa();
	}

	public List<Backlog> getBacklogList() {
		return backlogList;
	}

	public void setBacklogList(List<Backlog> backlogList) {
		this.backlogList = backlogList;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
