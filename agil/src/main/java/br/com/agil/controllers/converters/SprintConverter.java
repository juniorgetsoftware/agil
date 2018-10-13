package br.com.agil.controllers.converters;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import br.com.agil.models.Sprint;
import br.com.agil.services.SprintService;

@FacesConverter(forClass = Sprint.class)
public class SprintConverter implements Converter {

	private SprintService sprintService;

	public SprintConverter() {
		sprintService = BeanProvider.getContextualReference(SprintService.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Sprint sprint = null;
		if (isNotBlank(value)) {
			sprint = sprintService.buscarPorId(new Long(value));
		}
		return sprint;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (nonNull(value)) {
			Sprint sprint = (Sprint) value;
			return isNull(sprint.getId()) ? null : sprint.getId().toString();
		}
		return "";
	}

}
