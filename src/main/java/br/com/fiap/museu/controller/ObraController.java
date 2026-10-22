package br.com.fiap.museu.controller;

import br.com.fiap.museu.dto.ObraRequestDTO;
import br.com.fiap.museu.dto.ObraResponseDTO;
import br.com.fiap.museu.service.ObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @GetMapping
    public ResponseEntity<List<ObraResponseDTO>> listar() {
        return ResponseEntity.ok(obraService.listarObras());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ObraResponseDTO>> ranking() {
        return ResponseEntity.ok(obraService.listarRanking());
    }

    @PostMapping
    public ResponseEntity<ObraResponseDTO> cadastrar(@RequestBody ObraRequestDTO request) {
        ObraResponseDTO novaObra = obraService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaObra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraResponseDTO> atualizar(@PathVariable int id, @RequestBody ObraRequestDTO request) {
        return ResponseEntity.ok(obraService.atualizar(id, request));
    }

    @PostMapping("/{id}/votar")
    public ResponseEntity<Void> votar(@PathVariable int id) {
        obraService.votar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}