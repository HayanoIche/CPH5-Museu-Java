package br.com.fiap.museu.repository;

import br.com.fiap.museu.model.Obra;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ObraRepository {
    private List<Obra> obras = new ArrayList<>();
    private int nextId = 1;

    public List<Obra> findAll() {
        return obras;
    }

    public Optional<Obra> findById(int id) {
        return obras.stream().filter(o -> o.getId() == id).findFirst();
    }

    public Obra save(Obra obra) {
        if (obra.getId() == 0) {
            obra.setId(nextId++);
            obras.add(obra);
        } else {
            // Atualiza se já existir na lista
            for (int i = 0; i < obras.size(); i++) {
                if (obras.get(i).getId() == obra.getId()) {
                    obras.set(i, obra);
                    return obra;
                }
            }
            obras.add(obra);
        }
        return obra;
    }
}