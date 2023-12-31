package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService service;

    @GetMapping
    public ResponseEntity<List<PetDto>> listarTodosDisponiveis() {
        List<PetDto> pets = service.buscarPetsDisponiveis();
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<PetDto> SalvarPet(@RequestBody Abrigo abrigo, @RequestBody CadastroPetDto dto) {
        Pet pets = service.cadastrarPet(abrigo, dto);
        return ResponseEntity.ok(new PetDto(pets.getId(), pets.getTipo(), pets.getNome(), pets.getRaca(), pets.getIdade()));
    }

}
