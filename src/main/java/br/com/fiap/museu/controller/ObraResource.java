package br.com.fiap.museu.controller;

import br.com.fiap.museu.controller.dto.ObraResponseDto;
import br.com.fiap.museu.controller.dto.ObraMapper;
import br.com.fiap.museu.service.ObraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/obra")
@CrossOrigin(origins = "*")
public class ObraResource
{
    private final ObraService obraService;

    public ObraResource(ObraService obraService)
    {
        this.obraService = obraService;
    }

    @GetMapping
    public ResponseEntity<List<ObraResponseDto>> listarTodas()
    {
        List<ObraResponseDto> responseDtos = obraService.buscarTodasObras()
                .stream()
                .map(ObraMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(responseDtos);
    }
}
