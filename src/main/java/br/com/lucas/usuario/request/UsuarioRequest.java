package br.com.lucas.usuario.request;


import br.com.lucas.usuario.model.SenhaLimpa;
import br.com.lucas.usuario.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotNull @NotBlank @Email String email,
        @NotNull @NotBlank @Min(6) String senha
) {
    public Usuario toModel() {
        return new Usuario(email, new SenhaLimpa(senha));
    }
}
