package br.com.ensinofiapmensagens.services.interfaces;

import java.util.List;

public interface IEmailService {
	void enviarEmail(String mensagem, List<String> destinatarios);
}