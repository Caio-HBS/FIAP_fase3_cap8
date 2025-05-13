package br.com.fiap.upenergy.security.config;

import br.com.fiap.upenergy.model.Usuario;
import br.com.fiap.upenergy.repository.UsuarioRepository;
import br.com.fiap.upenergy.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class VerificarToken extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public VerificarToken(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = "";

        if (authorizationHeader == null) {
            token = null;
        } else {
            token = authorizationHeader.replace("Bearer", "").trim();
            String login = tokenService.validarToken(token);

            Optional<Usuario> testeUsuario = usuarioRepository.findByEmail(login);

            if (testeUsuario.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado.");
            }

            UserDetails usuario = testeUsuario.get();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
}
