package br.com.relatorio.services;

import java.util.List;

import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IEnvioEmailService;
import jakarta.inject.Inject;

public class EmailService implements IEmailService {

	@Inject
	private IEnvioEmailService envioEmailService;
	
	private final String REMETENTE = "DoNotReply@5acd0ae5-f401-4bb4-b1ad-b49425ca624f.azurecomm.net";
	private final String ASSUNTO = "Avaliação Urgente";	

	@Override
	public void enviarEmail(String mensagem, List<String> destinatarios) {
		envioEmailService.enviar(mensagem, destinatarios, REMETENTE, ASSUNTO);
	}
}	