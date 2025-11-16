package br.com.ensinofiapmensagens.services.interfaces;

import java.util.List;

public interface IEnvioEmailService {
	void enviar(String mensagem, List<String> destinatarios, String remetente, String assunto);
}