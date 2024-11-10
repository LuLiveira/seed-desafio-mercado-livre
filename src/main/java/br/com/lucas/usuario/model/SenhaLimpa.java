package br.com.lucas.usuario.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {
    private final String senha;

    public SenhaLimpa(@NotNull @NotBlank @Min(6) String senha) {
        Assert.hasLength(senha, "Senha não pode ser vazia");
        Assert.isTrue(senha.length() >= 6, "Senha deve possuir no minimo 6 caracteres");

        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
