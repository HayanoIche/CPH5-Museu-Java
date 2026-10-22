package br.com.fiap.museu.service;

import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.repository.EsculturaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsculturaService
{
    private final EsculturaRepository repository;

    @Autowired
    public EsculturaService(EsculturaRepository repository) {
        this.repository = repository;
    }

    public List<Escultura> buscarEsculturas()
    {
        return repository.getAll();
    }
}
