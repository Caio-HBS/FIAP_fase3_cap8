package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.model.Usuario;
import br.com.fiap.upenergy.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthorizationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return usuario.get();
    }

}
