package br.com.fiap.museu.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.museu.controller.dto.EsculturaMapper;
import br.com.fiap.museu.controller.dto.EsculturaRequestDto;
import br.com.fiap.museu.controller.dto.EsculturaResponseDto;
import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.service.EsculturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/obra/escultura")
@CrossOrigin(origins = "*")
public class EsculturaResource
{
    private EsculturaService service;

    @Autowired
    public EsculturaResource(EsculturaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<?> buscarEsculturas()
    {
        List<EsculturaResponseDto> esculturasDtos = service.buscarEsculturas().stream().map(EsculturaMapper::toResponseDto).toList();
        return ResponseEntity.ok(esculturasDtos);
    }

    @PostMapping
    public ResponseEntity<EsculturaResponseDto> cadastrar(@RequestBody EsculturaRequestDto requestDto)
    {
        Escultura escultura = EsculturaMapper.toModel(requestDto);
        Escultura esculturaSalva = service.salvar(escultura);
        EsculturaResponseDto responseDto = EsculturaMapper.toResponseDto(esculturaSalva);

        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping("/{id}/votar")
    public ResponseEntity<Void> votar(@PathVariable int id)
    {
        service.votar(id);
        return ResponseEntity.noContent().build(); // Retorna status 204
    }
}
