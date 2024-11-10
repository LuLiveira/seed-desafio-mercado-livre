package br.com.lucas.usuario;

import br.com.lucas.usuario.request.UsuarioRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    EntityManager manager;

    public UsuarioController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> post(@Valid @RequestBody UsuarioRequest request) {
        var usuario = request.toModel();
        manager.persist(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
