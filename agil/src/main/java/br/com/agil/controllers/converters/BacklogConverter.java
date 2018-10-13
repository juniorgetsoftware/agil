package br.com.agil.controllers.converters;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import br.com.agil.models.Backlog;
import br.com.agil.services.BacklogService;

@FacesConverter(forClass = Backlog.class)
public class BacklogConverter implements Converter {

	private BacklogService backlogService;

	public BacklogConverter() {
		backlogService = BeanProvider.getContextualReference(BacklogService.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Backlog backlog = null;
		if (isNotBlank(value)) {
			backlog = backlogService.buscarPorId(new Long(value));
		}
		return backlog;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (nonNull(value)) {
			Backlog backlog = (Backlog) value;
			return isNull(backlog.getId()) ? null : backlog.getId().toString();
		}
		return "";
	}

}
