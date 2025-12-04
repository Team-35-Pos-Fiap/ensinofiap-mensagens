package br.com.ensinofiapmensagens.resources;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ensinofiapmensagens.entities.db.UsuarioDB;
import br.com.ensinofiapmensagens.services.interfaces.IMensagemService;
import br.com.ensinofiapmensagens.services.interfaces.IUsuarioService;
import jakarta.inject.Inject;
import com.microsoft.azure.functions.annotation.FunctionName;

//@Path("/ensino/mensagens")
public class MensagemResource {

	@Inject
	private IMensagemService mensagemService;
	
    @Inject
	private IUsuarioService usuarioService;

    //@POST
    @FunctionName("fnc-ensino-mensagens")
	public void processarMensagens() throws InterruptedException {
    	mensagemService.processar(buscarEmailsDestinatarios());
    }

    private List<String> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		return usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}
}