package br.com.lucas.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
                   @NotNull @NotBlank String senha) {

        this.email = email;
        this.senha = senha;
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
