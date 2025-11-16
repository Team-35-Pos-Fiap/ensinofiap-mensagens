package br.com.ensinofiapmensagens.services;

import java.util.List;

import br.com.ensinofiapmensagens.entities.db.UsuarioDB;
import br.com.ensinofiapmensagens.repositories.interfaces.IUsuarioRepository;
import br.com.ensinofiapmensagens.services.interfaces.IUsuarioService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UsuarioService implements IUsuarioService {

    @Inject
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDB> buscarAdministradores() {
        return usuarioRepository.buscarUsuarios();
    }
}