package br.com.fiap.museu.controller;

import br.com.fiap.museu.controller.dto.*;
import br.com.fiap.museu.model.Fotografia;
import br.com.fiap.museu.service.FotografiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/obra/fotografia")
@CrossOrigin(origins = "*")
public class FotografiaResource {

    private final FotografiaService service;

    public FotografiaResource(FotografiaService fotografiaService) {
        this.service = fotografiaService;
    }

    @GetMapping
    public ResponseEntity<?> buscarFotografias()
    {
        List<FotografiaResponseDto> fotografiasDtos = service.buscarFotografias().stream().map(FotografiaMapper::toResponseDto).toList();
        return ResponseEntity.ok(fotografiasDtos);
    }

    @PostMapping
    public ResponseEntity<FotografiaResponseDto> criar(@RequestBody FotografiaRequestDto requestDto) {
        Fotografia fotografia = FotografiaMapper.toModel(requestDto);
        Fotografia salva = service.salvar(fotografia);
        return ResponseEntity.status(201).body(FotografiaMapper.toResponseDto(salva));
    }

    @PutMapping("/{id}/votar")
    public ResponseEntity<Void> votar(@PathVariable int id) {
        service.votar(id);
        return ResponseEntity.noContent().build();
    }
}
