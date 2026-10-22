package br.com.fiap.museu.model;

import java.time.LocalDate;

public class Pintura extends Obra
{
    // Atributos
    private double altura;
    private double largura;
    private String estilo;
    private String moldura;

    // Construtores
    public Pintura(String nome, String artista, String descricao, LocalDate dataCriacao, double altura, double largura, String estilo, String moldura) {
        super(nome, artista, descricao, dataCriacao);
        this.altura = altura;
        this.largura = largura;
        this.estilo = estilo;
        this.moldura = moldura;
    }

    public Pintura() {}

    // Acessores
    public double getAltura() { return altura; }
    public double getLargura() { return largura; }
    public String getEstilo() { return estilo; }
    public String getMoldura() { return moldura; }

    public void setAltura(double altura) { this.altura = altura; }
    public void setLargura(double largura) { this.largura = largura; }
    public void setEstilo(String estilo) { this.estilo = estilo; }
    public void setMoldura(String moldura) { this.moldura = moldura;}

    public double pegarTamanho()
    {
        return altura * largura;
    }
}
