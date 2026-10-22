package br.com.fiap.museu.controller.dto;

import java.time.LocalDate;

public record ObraResponseDto(
        int id,
        String nome,
        String artista,
        String descricao,
        int numeroDeVotos,
        LocalDate dataCriacao,
        String tipo
) {}
