package br.com.fiap.museu.service;

import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.model.Fotografia;
import br.com.fiap.museu.repository.FotografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FotografiaService
{
    private final FotografiaRepository repository;

    @Autowired
    public FotografiaService(FotografiaRepository repository)
    {
        this.repository = repository;
    }

    public List<Fotografia> buscarFotografias()
    {
        return repository.getAll();
    }

    public Fotografia salvar(Fotografia fotografia)
    {
        if (fotografia.getDataCriacao() != null && fotografia.getDataCriacao().isAfter(LocalDate.now()))
        {
            throw new RuntimeException("A data de criação não pode ser no futuro!");
        }

        repository.save(fotografia);
        return fotografia;
    }

    public void votar(int id)
    {
        repository.updateVotos(id);
    }
}
