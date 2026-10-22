package br.com.fiap.museu.controller;

import br.com.fiap.museu.controller.dto.*;
import br.com.fiap.museu.exception.ErrorResponse;
import br.com.fiap.museu.model.Fotografia;
import br.com.fiap.museu.service.FotografiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
    public ResponseEntity<Object> buscarFotografias() {
        try {
            List<FotografiaResponseDto> fotografiasDtos = service.buscarFotografias()
                    .stream()
                    .map(FotografiaMapper::toResponseDto)
                    .toList();
            return ResponseEntity.ok(fotografiasDtos);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorResponse error = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    "Erro ao buscar fotografias",
                    e.getMessage(),
                    "/api/v1/obra/fotografia"
            );
            return ResponseEntity.status(status).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody FotografiaRequestDto requestDto) {
        try {
            Fotografia fotografia = FotografiaMapper.toModel(requestDto);
            Fotografia salva = service.salvar(fotografia);
            FotografiaResponseDto responseDto = FotografiaMapper.toResponseDto(salva);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorResponse error = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    "Erro ao cadastrar",
                    e.getMessage(),
                    "/api/v1/obra/fotografia"
            );
            return ResponseEntity.status(status).body(error);
        }
    }

    @PutMapping("/{id}/votar")
    public ResponseEntity<Object> votar(@PathVariable int id) {
        try {
            service.votar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorResponse error = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    "Erro ao votar",
                    e.getMessage(),
                    "/api/v1/obra/fotografia"
            );
            return ResponseEntity.status(status).body(error);
        }
    }
}