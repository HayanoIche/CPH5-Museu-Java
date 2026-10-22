package br.com.fiap.museu.controller;

import br.com.fiap.museu.controller.dto.ObraResponseDto;
import br.com.fiap.museu.controller.dto.ObraMapper;
import br.com.fiap.museu.exception.ErrorResponse;
import br.com.fiap.museu.service.ObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/obra")
@CrossOrigin(origins = "*")
public class ObraResource {

    private final ObraService obraService;

    public ObraResource(ObraService obraService) {
        this.obraService = obraService;
    }

    @GetMapping
    public ResponseEntity<Object> listarTodas() {
        try {
            List<ObraResponseDto> responseDtos = obraService.buscarTodasObras()
                    .stream()
                    .map(ObraMapper::toResponseDto)
                    .toList();

            return ResponseEntity.ok(responseDtos);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorResponse error = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    "Erro ao listar obras",
                    e.getMessage(),
                    "/api/v1/obra"
            );
            return ResponseEntity.status(status).body(error);
        }
    }
}