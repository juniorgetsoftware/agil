package br.com.agil.jsf;

import javax.inject.Named;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@Named
@MessageBundle
public interface Msgs {

	@MessageTemplate("{cadastrado.sucesso}")
	String cadastradoComSucesso();

	@MessageTemplate("{editado.sucesso}")
	String editadoComSucesso();

	@MessageTemplate("{deletado.sucesso}")
	String deletadoComSucesso();
	
	@MessageTemplate("{status.alterado.sucesso}")
	String statusAlteradoSucesso();
}
