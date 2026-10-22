package br.com.fiap.museu.controller.dto;

import java.time.LocalDate;

public record EsculturaRequestDto(
        String nome,
        String artista,
        String descricao,
        LocalDate dataCriacao,
        String material,
        double pesoKG
) {}
