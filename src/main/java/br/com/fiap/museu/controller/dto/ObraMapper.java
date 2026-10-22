package br.com.fiap.museu.controller.dto;

import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.model.Fotografia;
import br.com.fiap.museu.model.Obra;

public class ObraMapper {

    public static ObraResponseDto toResponseDto(Obra obra) {
        if (obra == null) {
            return null;
        }

        String tipo = "DESCONHECIDO";
        if (obra instanceof Escultura) {
            tipo = "ESCULTURA";
        } else if (obra instanceof Fotografia) {
            tipo = "FOTOGRAFIA";
        }

        return new ObraResponseDto(
                obra.getId(),
                obra.getNome(),
                obra.getArtista(),
                obra.getDescricao(),
                obra.getNumeroDeVotos(),
                obra.getDataCriacao(),
                tipo
        );
    }
}
