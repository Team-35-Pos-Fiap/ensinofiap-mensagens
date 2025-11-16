package br.com.ensinofiapmensagens.services.interfaces;

import java.util.List;

import br.com.ensinofiapmensagens.entities.db.UsuarioDB;
import jakarta.enterprise.context.ApplicationScoped;

public interface IUsuarioService {
	List<UsuarioDB> buscarAdministradores();
}