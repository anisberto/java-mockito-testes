package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private ValidacaoPetDisponivel validacao;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Deveria permitir solicitar a adocao de um Pet")
    void deveriaPermitirSolicitarAdocaoPet(){

        // Arrange
        BDDMockito.given(this.petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);
        when(this.petRepository.findAll()).thenReturn(List.of());

        //Assert + ACT
        assertFalse(pet.getAdotado());
        assertDoesNotThrow(()-> this.validacao.validar(this.dto));
        assertEquals(0, this.petRepository.findAll().size());
    }

    @Test
    @DisplayName("Não Deveria permitir solicitar a adocao de um Pet")
    void naoDeveriaPermitirSolicitarAdocaoPet(){

        // Arrange
        BDDMockito.given(this.petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        //Assert + ACT
        assertThrows(ValidacaoException.class, ()-> this.validacao.validar(this.dto));
        assertTrue(pet.getAdotado());
    }

}