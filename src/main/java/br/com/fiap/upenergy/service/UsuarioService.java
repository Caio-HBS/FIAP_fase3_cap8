package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.dto.UsuarioCadastroDTO;
import br.com.fiap.upenergy.exception.NomeJaCadastradoException;
import br.com.fiap.upenergy.exception.RecursoNaoEncontradoException;
import br.com.fiap.upenergy.model.Usuario;
import br.com.fiap.upenergy.model.enums.UsuarioRole;
import br.com.fiap.upenergy.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(UsuarioCadastroDTO usuarioCadastroDTO) {

        Optional<Usuario> checarNomeUsuario = usuarioRepository.findByEmail(usuarioCadastroDTO.getEmail());

        if (checarNomeUsuario.isPresent()) {
            throw new NomeJaCadastradoException("Nome de usuário (e-mail) já está em uso.");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.getSenha());

        Usuario novoUsuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, novoUsuario);
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setRole(UsuarioRole.valueOf("USER"));

        return usuarioRepository.save(novoUsuario);

    }

    public void excluirUsuario(Long usuarioId) {

        try {
            Usuario usuarioEncontrado = usuarioRepository.findById(usuarioId).orElseThrow();
            usuarioRepository.delete(usuarioEncontrado);
        } catch (NoSuchElementException e) {
            throw new RecursoNaoEncontradoException("Usuario não encontrado.");
        }

    }

}
