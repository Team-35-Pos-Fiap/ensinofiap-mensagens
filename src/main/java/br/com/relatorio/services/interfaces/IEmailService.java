package br.com.relatorio.services.interfaces;

import java.util.List;

public interface IEmailService {
	void enviarEmail(String mensagem, List<String> destinatarios);
}