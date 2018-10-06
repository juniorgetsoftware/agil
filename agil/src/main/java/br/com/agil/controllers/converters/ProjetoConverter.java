package br.com.agil.controllers.converters;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import br.com.agil.models.Projeto;
import br.com.agil.services.ProjetoService;

@FacesConverter(forClass = Projeto.class)
public class ProjetoConverter implements Converter {

	private ProjetoService projetoService;

	public ProjetoConverter() {
		projetoService = BeanProvider.getContextualReference(ProjetoService.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Projeto projeto = null;
		if (isNotBlank(value)) {
			projeto = projetoService.buscarPorId(new Long(value));
		}
		return projeto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (nonNull(value)) {
			Projeto projeto = (Projeto) value;
			return isNull(projeto.getId()) ? null : projeto.getId().toString();
		}
		return "";
	}

}
