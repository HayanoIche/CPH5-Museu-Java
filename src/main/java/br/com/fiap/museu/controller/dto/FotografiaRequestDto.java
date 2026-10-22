package br.com.fiap.museu.controller.dto;

import java.time.LocalDate;

public record FotografiaRequestDto(
        String nome,
        String artista,
        String descricao,
        LocalDate dataCriacao,
        String camera,
        double resolucaoDPI,
        boolean colorida
) {}