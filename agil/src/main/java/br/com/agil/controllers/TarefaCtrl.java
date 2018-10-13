package br.com.agil.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.omnifaces.cdi.Param;

import br.com.agil.controllers.converters.TarefaConverter;
import br.com.agil.jsf.FacesUtil;
import br.com.agil.jsf.Msgs;
import br.com.agil.jsf.primefaces.LazyDataModel;
import br.com.agil.models.Tarefa;
import br.com.agil.services.TarefaService;

@Named
@ViewScoped
public class TarefaCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Param(name = "tarefa", converterClass = TarefaConverter.class)
	private Tarefa tarefa;

	private LazyDataModel<Tarefa, Long> tarefas;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private TarefaService tarefaService;

	@Inject
	private FacesUtil facesUtil;

	public String salvar() {
		tarefaService.salvar(tarefa);
		msgs.addInfo().cadastradoComSucesso();
		tarefa = null;
		facesUtil.atualizarComponente("msgs");
		return null;
	}

	public void alterarStatus() {
		tarefaService.alterarStatus(tarefa);
		msgs.addInfo().statusAlteradoSucesso();
		facesUtil.atualizarComponente("msgs");
	}
	
	public String editar() {
		tarefaService.editar(tarefa);
		msgs.addInfo().editadoComSucesso();
		tarefa = null;
		facesUtil.atualizarComponente("msgs");
		return "/private/tarefa/list.xhtml?faces-redirect=true";
	}

	public void listar() {
		if (facesUtil.isNotPostback() || isNull(tarefas)) {
			this.tarefas = new LazyDataModel<>(tarefaService.getRepository());
		}
	}

	public Tarefa getTarefa() {
		if (isNull(tarefa))
			tarefa = new Tarefa();
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public LazyDataModel<Tarefa, Long> getTarefas() {
		return tarefas;
	}

	public void setTarefas(LazyDataModel<Tarefa, Long> tarefas) {
		this.tarefas = tarefas;
	}

	public boolean isEditando() {
		return nonNull(getTarefa()) && nonNull(getTarefa().getId());
	}

}
