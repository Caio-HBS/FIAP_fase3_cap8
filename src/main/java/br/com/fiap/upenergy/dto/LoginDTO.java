package br.com.fiap.upenergy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "O e-mail do usuário é obrigatório!")
    @Email(message = "O e-mail do usuário não é válido!")
    String email;

    @NotBlank(message = "A senha é obrigatório")
    @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
    String senha;

}
