package br.com.fiap.museu.service;

import br.com.fiap.museu.model.Obra;
import br.com.fiap.museu.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraService
{
    private final ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository)
    {
        this.obraRepository = obraRepository;
    }

    public List<Obra> buscarTodasObras()
    {
        return obraRepository.getAllObras();
    }
}