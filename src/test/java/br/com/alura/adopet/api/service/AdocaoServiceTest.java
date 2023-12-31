package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;
    @Mock
    private AdocaoRepository repository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<ValidacaoSolicitacaoAdocao>();

    private SolicitacaoAdocaoDto dto;

    @Mock
    private ValidacaoSolicitacaoAdocao valOne;

    @Mock
    private ValidacaoSolicitacaoAdocao valTwo;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Mock
    private Adocao adocao;

    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    @DisplayName("Deve salvar adoção")
    void deveSalvcarAdocao(){

        // Arrange
        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Qualquer");
        BDDMockito.given(this.petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(this.tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        // ACT
         adocaoService.solicitar(dto);

        //Assert
        then(repository).should().save(adocaoCaptor.capture());
        Adocao adocaoSalva = adocaoCaptor.getValue();
        Assertions.assertEquals(pet, adocaoSalva.getPet());
        Assertions.assertEquals(tutor, adocaoSalva.getTutor());
        Assertions.assertEquals(dto.motivo(), adocaoSalva.getMotivo());
    }

    @Test
    @DisplayName("Deve executar validações")
    void deveExecutarValidacoes(){

        // Arrange
        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Qualquer");
        BDDMockito.given(this.petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(this.tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        validacoes.add(valOne);
        validacoes.add(valTwo);

        // ACT
        adocaoService.solicitar(dto);

        //Assert
        BDDMockito.then(valOne).should().validar(dto);
        BDDMockito.then(valTwo).should().validar(dto);
    }

}