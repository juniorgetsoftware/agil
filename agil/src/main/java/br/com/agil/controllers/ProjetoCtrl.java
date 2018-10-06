package br.com.agil.controllers;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.omnifaces.cdi.Param;

import br.com.agil.controllers.converters.ProjetoConverter;
import br.com.agil.jsf.FacesUtil;
import br.com.agil.jsf.Msgs;
import br.com.agil.jsf.primefaces.LazyDataModel;
import br.com.agil.models.Projeto;
import br.com.agil.services.ProjetoService;

@Named
@ViewScoped
public class ProjetoCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Param(name = "projeto", converterClass = ProjetoConverter.class)
	private Projeto projeto;

	private LazyDataModel<Projeto, Long> projetos;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private ProjetoService projetoService;

	@Inject
	private FacesUtil facesUtil;

	public String salvar() {
		projetoService.salvar(projeto);
		msgs.addInfo().cadastradoComSucesso();
		projeto = null;
		facesUtil.atualizarComponente("msgs");
		return null;
	}

	public void alterarStatus() {
		projetoService.alterarStatus(projeto);
		msgs.addInfo().statusAlteradoSucesso();
		facesUtil.atualizarComponente("msgs");
	}
	
	public String editar() {
		projetoService.editar(projeto);
		msgs.addInfo().editadoComSucesso();
		projeto = null;
		facesUtil.atualizarComponente("msgs");
		return "/private/projeto/list.xhtml?faces-redirect=true";
	}

	public void listar() {
		if (facesUtil.isNotPostback() || isNull(projetos)) {
			this.projetos = new LazyDataModel<>(projetoService.getRepository());
		}
	}

	public Projeto getProjeto() {
		if (isNull(projeto))
			projeto = new Projeto();
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public LazyDataModel<Projeto, Long> getProjetos() {
		return projetos;
	}

	public void setProjetos(LazyDataModel<Projeto, Long> projetos) {
		this.projetos = projetos;
	}

	public boolean isEditando() {
		return nonNull(getProjeto()) && nonNull(getProjeto().getId());
	}

}
