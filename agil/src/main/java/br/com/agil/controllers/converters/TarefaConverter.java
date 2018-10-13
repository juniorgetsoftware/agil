package br.com.agil.controllers.converters;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import br.com.agil.models.Tarefa;
import br.com.agil.services.TarefaService;

@FacesConverter(forClass = Tarefa.class)
public class TarefaConverter implements Converter {

	private TarefaService tarefaService;

	public TarefaConverter() {
		tarefaService = BeanProvider.getContextualReference(TarefaService.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Tarefa tarefa = null;
		if (isNotBlank(value)) {
			tarefa = tarefaService.buscarPorId(new Long(value));
		}
		return tarefa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (nonNull(value)) {
			Tarefa tarefa = (Tarefa) value;
			return isNull(tarefa.getId()) ? null : tarefa.getId().toString();
		}
		return "";
	}

}
