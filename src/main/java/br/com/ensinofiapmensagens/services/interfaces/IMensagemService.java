package br.com.ensinofiapmensagens.services.interfaces;

import java.util.List;

public interface IMensagemService {
	void processar(List<String> destinatarios) throws InterruptedException;
}