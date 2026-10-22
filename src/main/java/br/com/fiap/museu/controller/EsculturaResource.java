package br.com.fiap.museu.controller;

import java.time.Instant;
import java.util.List;

import br.com.fiap.museu.controller.dto.EsculturaMapper;
import br.com.fiap.museu.controller.dto.EsculturaRequestDto;
import br.com.fiap.museu.controller.dto.EsculturaResponseDto;
import br.com.fiap.museu.exception.ErrorResponse;
import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.service.EsculturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/obra/escultura")
@CrossOrigin(origins = "*")
public class EsculturaResource {

    private final EsculturaService service;

    @Autowired
    public EsculturaResource(EsculturaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> buscarEsculturas() {
        try {
            List<EsculturaResponseDto> esculturasDtos = service.buscarEsculturas()
                    .stream()
                    .map(EsculturaMapper::toResponseDto)
                    .toList();
            return ResponseEntity.ok(esculturasDtos);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorResponse error = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    "Erro ao buscar esculturas",
                    e.getMessage(),
                    "/api/v1/obra/escultura"
            );
            return ResponseEntity.status(status).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody EsculturaRequestDto requestDto) {
        try {
            Escultura escultura = EsculturaMapper.toModel(requestDto);
            Escultura esculturaSalva = service.salvar(escultura);
            EsculturaResponseDto responseDto = EsculturaMapper.toResponseDto(esculturaSalva);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ErrorResponse error = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    "Erro ao cadastrar",
                    e.getMessage(),
                    "/api/v1/obra/escultura"
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
                    "/api/v1/obra/escultura"
            );
            return ResponseEntity.status(status).body(error);
        }
    }
}