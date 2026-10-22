package br.com.fiap.museu.controller.dto;

import java.time.LocalDate;

public record EsculturaResponseDto(
        int id,
        String nome,
        String artista,
        String descricao,
        int numeroDeVotos,
        LocalDate dataCriacao,
        String material,
        double pesoKG
) {}
