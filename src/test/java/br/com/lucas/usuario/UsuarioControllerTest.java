package br.com.lucas.usuario;

import br.com.lucas.usuario.model.Usuario;
import br.com.lucas.usuario.request.UsuarioRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EntityManager entityManager;

    @DisplayName("Deve cadastrar um novo usuario")
    @Test
    void teste1() throws Exception {
        doNothing().when(entityManager).persist(any(Usuario.class));

        mockMvc.perform(post("/usuarios").
                            contentType(MediaType.APPLICATION_JSON).
                            content("{\"email\": \"lcsd.lucas@gmail.com\", \"senha\": \"123456\"}"))
                .andExpect(status().isCreated());
    }

    @DisplayName("Deve criptografar a senha do Usuario")
    @Test
    void teste2() {
        UsuarioController usuarioController = new UsuarioController(entityManager);

        doNothing().when(entityManager).persist(any(Usuario.class));
        var usuarioRequest = new UsuarioRequest("lcsd.lucas@gmail.com", "123456");
        usuarioController.post(usuarioRequest);

        verify(entityManager, times(1)).persist(any(Usuario.class));

        ArgumentCaptor<Usuario> argumentCaptor = forClass(Usuario.class);
        verify(entityManager).persist(argumentCaptor.capture());

        Usuario usuarioCapturado = argumentCaptor.getValue();
        assertEquals("lcsd.lucas@gmail.com", usuarioCapturado.getEmail());
        assertTrue(new BCryptPasswordEncoder().matches(usuarioRequest.senha(), usuarioCapturado.getSenha()));

    }
}