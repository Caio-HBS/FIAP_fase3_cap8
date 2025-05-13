package br.com.fiap.upenergy.controller;

import br.com.fiap.upenergy.dto.LoginDTO;
import br.com.fiap.upenergy.dto.TokenDTO;
import br.com.fiap.upenergy.model.Usuario;
import br.com.fiap.upenergy.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO usuarioDto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                        usuarioDto.getEmail(), usuarioDto.getSenha()
        );

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

}
