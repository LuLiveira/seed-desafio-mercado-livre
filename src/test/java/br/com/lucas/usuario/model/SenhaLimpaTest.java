package br.com.lucas.usuario.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SenhaLimpaTest {

    @DisplayName("Só aceita senha com 6 ou mais caracteres")
    @ParameterizedTest
    @ValueSource(strings = {
            "123456",
            "1234567",
            "123456789999999"
    })
    void teste1(String senhaLimpa) {
        new SenhaLimpa(senhaLimpa);
    }

    @DisplayName("Não aceita senha com menos de 6 caracteres")
    @ParameterizedTest
    @ValueSource(strings = {"12345", "12"})
    void test2(String senhaLimpa) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SenhaLimpa(senhaLimpa));
    }


}