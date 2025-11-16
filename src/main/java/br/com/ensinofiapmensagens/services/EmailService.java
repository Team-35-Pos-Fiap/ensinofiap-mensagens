package br.com.ensinofiapmensagens.services;

import java.util.List;

import br.com.ensinofiapmensagens.services.interfaces.IEmailService;
import br.com.ensinofiapmensagens.services.interfaces.IEnvioEmailService;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class EmailService implements IEmailService {

	private IEnvioEmailService envioEmailService;
	
	public EmailService() {
		this.envioEmailService = new EnvioEmailService();
	}

	private final String REMETENTE = "DoNotReply@5acd0ae5-f401-4bb4-b1ad-b49425ca624f.azurecomm.net";
	private final String ASSUNTO = "Avaliação Urgente";	

	@Override
	public void enviarEmail(String mensagem, List<String> destinatarios) {
		envioEmailService.enviar(mensagem, destinatarios, REMETENTE, ASSUNTO);
	}
}	