package br.com.fiap.museu.exception;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp, //Momento exato do erro
        int status, //codigo HTTP
        String error, //Titulo generico do erro
        String message, //Mensagem detalhada
        String path // A url que foi chamada
) {}
