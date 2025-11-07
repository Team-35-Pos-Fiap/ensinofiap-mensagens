package br.com.relatorio.services;

import java.util.List;
import java.util.stream.Collectors;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

import br.com.relatorio.entities.db.UsuarioDB;
import br.com.relatorio.services.interfaces.IEmailService;
import br.com.relatorio.services.interfaces.IMensagemService;
import br.com.relatorio.services.interfaces.IUsuarioService;
import jakarta.inject.Inject;

public class MensagensService implements IMensagemService {

	@Inject
	private static IEmailService emailService;
	
	@Inject
	private static IUsuarioService usuarioService;

    public static final String CONEXAO = "Endpoint=sb://ensino.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=6zkucEfA77L65NlTWaEB8EVwNuBHZESBU+ASbLyLemo=";
    public static final String FILA = "avaliacoes-urgentes";
	
	@Override
	public void processar() {
		ServiceBusProcessorClient client = new ServiceBusClientBuilder().connectionString(CONEXAO)
										                				.processor()
														                .queueName(FILA)
														                .processMessage(MensagensService::processarMensagem)
														                .buildProcessorClient();
		client.start();
		client.stop();
	}
		
	private static void processarMensagem(ServiceBusReceivedMessageContext dados) {
        ServiceBusReceivedMessage mensagem = dados.getMessage();
        
        enviarEmail(mensagem.getBody().toString());
        
        dados.complete();
	}
	
	private static List<String> buscarEmailsDestinatarios() {
		List<UsuarioDB> usuarios = usuarioService.buscarAdministradores();
		
		return usuarios.stream().map(u -> u.getEmail()).collect(Collectors.toList());
	}
	
	private static void enviarEmail(String mensagem) {
		emailService.enviarEmail(mensagem, buscarEmailsDestinatarios());
	}
}