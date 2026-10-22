package br.com.fiap.museu.service;

import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.repository.EsculturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EsculturaService
{
    private final EsculturaRepository repository;

    @Autowired
    public EsculturaService(EsculturaRepository repository)
    {
        this.repository = repository;
    }

    public List<Escultura> buscarEsculturas()
    {
        return repository.getAll();
    }

    public Escultura salvar(Escultura escultura)
    {
        if (escultura.getDataCriacao() != null && escultura.getDataCriacao().isAfter(LocalDate.now()))
        {
            throw new RuntimeException("A data de criação não pode ser no futuro!");
        }

        repository.save(escultura);
        return escultura;
    }

    public void votar(int id)
    {
        repository.updateVotos(id);
    }
}
