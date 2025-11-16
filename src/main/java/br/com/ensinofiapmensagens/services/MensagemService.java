package br.com.ensinofiapmensagens.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

import br.com.ensinofiapmensagens.services.interfaces.IEmailService;
import br.com.ensinofiapmensagens.services.interfaces.IMensagemService;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MensagemService implements IMensagemService {

	private static IEmailService emailService;
			
	private static List<String> destinatarios = null;

	public MensagemService() {
		emailService = new EmailService();
	}

    private final String CONEXAO = "Endpoint=sb://ensino.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=6zkucEfA77L65NlTWaEB8EVwNuBHZESBU+ASbLyLemo=";
    private final String FILA = "avaliacoes-urgentes";
	
	@Override
	public void processar(List<String> emailsDestinatarios) throws InterruptedException {
		destinatarios = emailsDestinatarios;

		ServiceBusProcessorClient processadorMensagens = new ServiceBusClientBuilder().connectionString(CONEXAO)
																					  .processor()
																					  .queueName(FILA)
																					  .processMessage(MensagemService::processarMensagem)
																					  .processError(e -> {
																						  System.out.println("Erro ao processar mensagem: " + e.getException().getMessage());
																					  })
																					  .buildProcessorClient();
		processadorMensagens.start();
		
		esperarProcessamentoMensagens();
		
		processadorMensagens.stop();
		processadorMensagens.close();	
	}
		
	private static void processarMensagem(ServiceBusReceivedMessageContext dados) {
        enviarEmail(dados.getMessage().getBody().toString());

        dados.complete();
	}
	
	private static void enviarEmail(String mensagem) {
		emailService.enviarEmail(mensagem, destinatarios);
	}

	private static void esperarProcessamentoMensagens() throws InterruptedException {
		TimeUnit.SECONDS.sleep(60);
	}
}