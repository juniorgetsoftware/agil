package br.com.agil.controllers.converters;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.agil.models.EntidadeBase;

@FacesConverter(forClass = EntidadeBase.class, value = "genericConverter")
public class GenericConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent componente, String value) {
		return value != null ? componente.getAttributes().get(value) : null;
	}

	public String getAsString(FacesContext ctx, UIComponent componente, Object value) {
		if (value != null) {
			EntidadeBase entity = (EntidadeBase) value;
			if (entity.getId() != null) {
				this.addAttribute(componente, entity);
				if (entity.getId() != null) {
					return String.valueOf(entity.getId());
				}
				return (String) value;
			}
		}

		return "";
	}

	private void addAttribute(UIComponent componente, EntidadeBase o) {
		this.getAttributesFrom(componente).put(o.getId().toString(), o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent componente) {
		return componente.getAttributes();
	}

}