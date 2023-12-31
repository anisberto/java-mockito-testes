package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService adocaoService;

    @Test
    @DisplayName("Deveria devolver codigo 400 para solicitacao de adocao com erros")
    void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {

        // Arrange
        String jsonBody = "{}";

        // ACT
        var response = mvc.perform(
                post("/adocoes")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Deveria devolver codigo 200 para solicitacao de adocao sem erros")
    void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {

        // Arrange
        String jsonBody = """
            {
                "idPet": 1,
                "idTutor": 1,
                "motivo": "Motivo qualquer"
            }
            """;

        // ACT
        var response = mvc.perform(
                post("/adocoes")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}