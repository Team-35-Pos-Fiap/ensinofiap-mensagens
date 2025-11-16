package br.com.ensinofiapmensagens.repositories.interfaces;

import java.util.List;

import br.com.ensinofiapmensagens.entities.db.UsuarioDB;

public interface IUsuarioRepository {
	List<UsuarioDB> buscarUsuarios();
}