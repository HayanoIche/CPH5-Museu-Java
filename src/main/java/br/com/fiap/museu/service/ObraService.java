package br.com.fiap.museu.service;

import br.com.fiap.museu.dto.ObraRequestDTO;
import br.com.fiap.museu.dto.ObraResponseDTO;
import br.com.fiap.museu.model.*;
import br.com.fiap.museu.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObraService {

    private final ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    public List<ObraResponseDTO> listarObras() {
        return obraRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ObraResponseDTO> listarRanking() {
        return obraRepository.findAll().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getNumeroDeVotos(), o1.getNumeroDeVotos()))
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public ObraResponseDTO cadastrar(ObraRequestDTO request) {
        Obra obra = criarInstanciaPorTipo(request.getTipo());
        preencherDados(obra, request);
        obraRepository.save(obra);
        return converterParaDTO(obra);
    }

    public ObraResponseDTO atualizar(int id, ObraRequestDTO request) {
        Obra obra = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada com ID: " + id));

        preencherDados(obra, request);
        obraRepository.save(obra);
        return converterParaDTO(obra);
    }

    public void votar(int id) {
        Obra obra = obraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada com ID: " + id));
        obra.incrementarVotos();
        obraRepository.save(obra);
    }

    private Obra criarInstanciaPorTipo(String tipo) {
        if (tipo == null) return new Pintura();
        switch (tipo.toUpperCase()) {
            case "ESCULTURA": return new Escultura();
            case "FOTOGRAFIA": return new Fotografia();
            default: return new Pintura();
        }
    }

    private void preencherDados(Obra obra, ObraRequestDTO request) {
        obra.setNome(request.getTitulo());
        obra.setArtista(request.getAutor());
        obra.setDescricao(request.getDetalhesEspecificos());
    }

    private ObraResponseDTO converterParaDTO(Obra obra) {
        String tipo = obra.getClass().getSimpleName().toUpperCase();
        return new ObraResponseDTO(
                (long) obra.getId(),
                obra.getNome(),
                obra.getArtista(),
                0,
                tipo,
                obra.getDescricao(),
                obra.getNumeroDeVotos()
        );
    }
}