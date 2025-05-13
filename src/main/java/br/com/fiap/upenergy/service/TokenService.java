package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Em um ambiente de produção, esse segredo seria uma variável de ambiente.
    private final String palavraSecreta = "400bbc64ebaf4e741790f2633348811fb5fd1549a99031617200a2976ec789db";

    public String gerarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            String token = JWT.create()
                    .withIssuer("UpEnergy")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token!");
        }

    }

    private Instant gerarDataDeExpiracao(){
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT.require(algorithm)
                    .withIssuer("UpEnergy")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e){
            return "";
        }
    }

}
