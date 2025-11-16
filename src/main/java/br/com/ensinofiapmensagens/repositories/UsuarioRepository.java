package br.com.ensinofiapmensagens.repositories;

import java.util.List;

import br.com.ensinofiapmensagens.entities.db.UsuarioDB;
import br.com.ensinofiapmensagens.repositories.interfaces.IUsuarioRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UsuarioRepository implements IUsuarioRepository{

	@Override
	public List<UsuarioDB> buscarUsuarios() {
		return UsuarioDB.buscarUsuariosAdministradores();
	}
}