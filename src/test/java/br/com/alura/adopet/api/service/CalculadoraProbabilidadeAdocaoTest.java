package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    /**
     *
     * Pattern Triple 'A'
     * Arrange | ACT (Action) | Assert
     *
     * Pattern GWT
     * Given, When, Then
     *
     * **/

    @Test
    @DisplayName("Deve retornar Alta Probabilidade para Pet com Idade e Peso Baixo")
    void deveRetornarAltaProbabilidadeAltaParaPetComIdadeEPesoBaixo(){
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo Felix", "62992947507","anisbertoos@gmail.com"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Auau", "Siames", 1, "gray", 4.0f), abrigo);

        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);
        assertEquals(probabilidadeAdocao.ALTA , probabilidadeAdocao);
    }

    @Test
    @DisplayName("Deve retornar Media Probabilidade para Pet com Idade Alta e Peso Baixo")
    void deveRetornarAltaProbabilidadeMediaParaPetComIdadeEPesoBaixo(){
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo Felix", "62992947507","anisbertoos@gmail.com"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.CACHORRO, "Auau", "Siames", 15, "gray", 4.0f), abrigo);

        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);
        assertEquals(probabilidadeAdocao.MEDIA , probabilidadeAdocao);
    }
}