package br.com.fiap.museu.controller.dto;

import br.com.fiap.museu.controller.dto.FotografiaRequestDto;
import br.com.fiap.museu.controller.dto.FotografiaResponseDto;
import br.com.fiap.museu.model.Fotografia;

public class FotografiaMapper
{
    public static Fotografia toModel(FotografiaRequestDto dto) {
        if (dto == null) return null;
        return new Fotografia(
                dto.nome(),
                dto.artista(),
                dto.descricao(),
                dto.dataCriacao(),
                dto.colorida(),
                dto.resolucaoDPI(),
                dto.camera()
        );
    }

    public static FotografiaResponseDto toResponseDto(Fotografia fotografia) {
        if (fotografia == null) return null;
        return new FotografiaResponseDto(
                fotografia.getId(),
                fotografia.getNome(),
                fotografia.getArtista(),
                fotografia.getDescricao(),
                fotografia.getNumeroDeVotos(),
                fotografia.getDataCriacao(),
                fotografia.getCamera(),
                fotografia.getResolucaoDPI(),
                fotografia.isColorida()
        );
    }
}
