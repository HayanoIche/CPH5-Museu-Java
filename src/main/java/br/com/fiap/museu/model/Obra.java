package br.com.fiap.museu.model;

import java.time.LocalDate;

public abstract class Obra
{
    // Atributos
    private int id;
    private String nome;
    private String artista;
    private String descricao;
    private int numeroDeVotos;
    private LocalDate dataCriacao;

    // Construtor
    protected Obra(String nome, String artista, String descricao, LocalDate dataCriacao) {
        this.nome = nome;
        this.artista = artista;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public Obra() {
    }

    // Acessores
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getArtista() { return artista; }
    public String getDescricao() { return descricao; }
    public int getNumeroDeVotos() { return numeroDeVotos; }
    public LocalDate getDataCriacao() { return dataCriacao; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setArtista(String artista) { this.artista = artista; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setNumeroDeVotos(int numeroDeVotos) { this.numeroDeVotos = numeroDeVotos; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    // Workers
    public void incrementarVotos() { numeroDeVotos += 1; }
}
