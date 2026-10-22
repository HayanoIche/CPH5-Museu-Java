package br.com.fiap.museu.controller.dto;

import java.time.LocalDate;

public record FotografiaResponseDto(
        int id,
        String nome,
        String artista,
        String descricao,
        int numeroDeVotos,
        LocalDate dataCriacao,
        String camera,
        double resolucaoDPI,
        boolean colorida
) {}