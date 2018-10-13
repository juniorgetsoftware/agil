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

import br.com.agil.controllers.converters.ProdutoConverter;
import br.com.agil.jsf.FacesUtil;
import br.com.agil.jsf.Msgs;
import br.com.agil.jsf.primefaces.LazyDataModel;
import br.com.agil.models.Produto;
import br.com.agil.services.ProdutoService;

@Named
@ViewScoped
public class ProdutoCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Param(name = "produto", converterClass = ProdutoConverter.class)
	private Produto produto;

	private LazyDataModel<Produto, Long> produtos;
	private List<Produto> produtosList;

	@Inject
	private JsfMessage<Msgs> msgs;

	@Inject
	private ProdutoService produtoService;

	@Inject
	private FacesUtil facesUtil;

	public String salvar() {
		produtoService.salvar(produto);
		msgs.addInfo().cadastradoComSucesso();
		produto = null;
		facesUtil.atualizarComponente("msgs");
		return null;
	}

	public void alterarStatus() {
		produtoService.alterarStatus(produto);
		msgs.addInfo().statusAlteradoSucesso();
		facesUtil.atualizarComponente("msgs");
	}
	
	public String editar() {
		produtoService.editar(produto);
		msgs.addInfo().editadoComSucesso();
		produto = null;
		facesUtil.atualizarComponente("msgs");
		return "/private/produto/list.xhtml?faces-redirect=true";
	}

	public void listar() {
		if (facesUtil.isNotPostback() || isNull(produtos)) {
			this.produtos = new LazyDataModel<>(produtoService.getRepository());
		}
	}

	public void listarSemDemanda() {
		if (facesUtil.isNotPostback() || isNull(produtosList)) {
			this.produtosList = produtoService.listar();
		}
	}

	public Produto getProduto() {
		if (isNull(produto))
			produto = new Produto();
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LazyDataModel<Produto, Long> getProdutos() {
		return produtos;
	}

	public void setProdutos(LazyDataModel<Produto, Long> produtos) {
		this.produtos = produtos;
	}

	public boolean isEditando() {
		return nonNull(getProduto()) && nonNull(getProduto().getId());
	}

	public List<Produto> getProdutosList() {
		return produtosList;
	}

	public void setProdutosList(List<Produto> produtosList) {
		this.produtosList = produtosList;
	}

}
