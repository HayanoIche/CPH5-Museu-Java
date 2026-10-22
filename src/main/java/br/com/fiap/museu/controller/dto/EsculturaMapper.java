package br.com.fiap.museu.controller.dto;

import br.com.fiap.museu.controller.dto.EsculturaRequestDto;
import br.com.fiap.museu.model.Escultura;

public class EsculturaMapper
{
    public static Escultura toModel(EsculturaRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return new Escultura(
                dto.nome(),
                dto.artista(),
                dto.descricao(),
                dto.dataCriacao(),
                dto.material(),
                dto.pesoKG()
        );
    }

    public static EsculturaResponseDto toResponseDto(Escultura escultura) {
        if (escultura == null) {
            return null;
        }
        return new EsculturaResponseDto(
                escultura.getId(),
                escultura.getNome(),
                escultura.getArtista(),
                escultura.getDescricao(),
                escultura.getNumeroDeVotos(),
                escultura.getDataCriacao(),
                escultura.getMaterial(),
                escultura.getPesoKG()
        );
    }
}
