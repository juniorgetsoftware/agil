package br.com.agil.controllers.converters;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import br.com.agil.models.Produto;
import br.com.agil.services.ProdutoService;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	private ProdutoService produtoService;

	public ProdutoConverter() {
		produtoService = BeanProvider.getContextualReference(ProdutoService.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto produto = null;
		if (isNotBlank(value)) {
			produto = produtoService.buscarPorId(new Long(value));
		}
		return produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (nonNull(value)) {
			Produto produto = (Produto) value;
			return isNull(produto.getId()) ? null : produto.getId().toString();
		}
		return "";
	}

}
