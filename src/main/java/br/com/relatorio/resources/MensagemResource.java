package br.com.relatorio.resources;

import br.com.relatorio.services.interfaces.IMensagemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/mensagens")
public class MensagemResource {

	@Inject
	private IMensagemService mensagemService;
	
    @POST
    public void processarMensagens() {
    	mensagemService.processar();
    }
}