package br.com.ensinofiapmensagens.resources;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ensinofiapmensagens.entities.db.UsuarioDB;
import br.com.ensinofiapmensagens.services.interfaces.IMensagemService;
import br.com.ensinofiapmensagens.services.interfaces.IUsuarioService;
import io.quarkus.funqy.Funq;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

//@Path("/ensino/mensagens")
public class MensagemResource {

	@Inject
	private IMensagemService mensagemService;
	
    @Inject
	private IUsuarioService usuarioService;

    //@POST
	@Funq("processar")
    public void processarMensagens() throws InterruptedException {
    	mensagemService.processar(buscarEmailsDestinatarios());
    }

    private List<String> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		return usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}
}