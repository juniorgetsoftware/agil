package br.com.agil.controllers;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.omnifaces.cdi.Param;

import br.com.agil.controllers.converters.ProdutoConverter;
import br.com.agil.jsf.FacesUtil;
import br.com.agil.jsf.Msgs;
import br.com.agil.models.Backlog;
import br.com.agil.models.Produto;
import br.com.agil.models.StatusBacklog;
import br.com.agil.services.BacklogService;

@Named
@ViewScoped
public class KanbanCtrl implements Serializable {

	private static final long serialVersionUID = -8936546368008010500L;

	private Backlog backlog;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private FacesUtil facesUtil;

	@Inject
	@Param(name = "produto", converterClass = ProdutoConverter.class)
	private Produto produto;

	@Inject
	private BacklogService backlogService;

	private List<Backlog> backlogs;

	public void listarBacklogs() {
		if (Objects.isNull(backlogs) || facesUtil.isNotPostback()) {
			backlogs = backlogService.backlogsPorProduto(produto);
		}
	}

	public List<Backlog> backlogs(StatusBacklog statusBacklog) {
		return backlogs.stream().filter(b -> b.getStatusBacklog().equals(statusBacklog)).collect(toList());
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	//

	public String statusAFazer() {
		backlogService.statusAFazer(backlog);
		msgs.addInfo().statusAlteradoSucesso();
		return null;
	}

	public String statusCancelar() {
		backlogService.statusCancelar(backlog);
		msgs.addInfo().statusAlteradoSucesso();
		return null;
	}

	public String statusConcluido() {
		backlogService.statusConcluido(backlog);
		msgs.addInfo().statusAlteradoSucesso();
		return null;
	}

	public String statusEmAndamento() {
		backlogService.statusEmAndamento(backlog);
		msgs.addInfo().statusAlteradoSucesso();
		return null;
	}

	public String statusNaoAtribuido() {
		backlogService.statusNaoAtribuido(backlog);
		msgs.addInfo().statusAlteradoSucesso();
		return null;
	}

	public Backlog getBacklog() {
		if(isNull(backlog)) backlog = new Backlog();
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

}