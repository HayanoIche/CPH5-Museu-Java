package br.com.fiap.museu.dto;

public class ObraRequestDTO {
    private String titulo;
    private String autor;
    private int ano;
    private String tipo; // Ex: "PINTURA", "ESCULTURA", "FOTOGRAFIA"
    private String detalhesEspecificos; // Atributo extra para a dica do enunciado

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDetalhesEspecificos() { return detalhesEspecificos; }
    public void setDetalhesEspecificos(String detalhesEspecificos) { this.detalhesEspecificos = detalhesEspecificos; }
}