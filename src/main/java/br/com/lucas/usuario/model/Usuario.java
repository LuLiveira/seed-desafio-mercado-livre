package br.com.lucas.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.Instant;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private Instant instante;

    public Usuario(@NotNull @NotBlank @Email String email,
                   @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email), "E-mail não pode ser vazio");
        Assert.notNull(senhaLimpa, "O objeto SenhaLimpa não pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

    @PrePersist
    public void prePersist() {
        this.instante = Instant.now();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Instant getInstante() {
        return instante;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setInstante(Instant instante) {
        this.instante = instante;
    }
}
