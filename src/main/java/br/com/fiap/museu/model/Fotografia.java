package br.com.fiap.museu.model;

import java.time.LocalDate;

public class Fotografia extends Obra
{
    // Acessores
    private String camera;
    private double resolucaoDPI;
    private boolean colorida;

    // Construtores
    public Fotografia(String nome, String artista, String descricao, LocalDate dataCriacao, boolean colorida, double resolucaoDPI, String camera) {
        super(nome, artista, descricao, dataCriacao);
        this.colorida = colorida;
        this.resolucaoDPI = resolucaoDPI;
        this.camera = camera;
    }

    public Fotografia() {}

    // Acessores
    public String getCamera() { return camera; }
    public double getResolucaoDPI() { return resolucaoDPI; }
    public boolean isColorida() { return colorida; }

    public void setCamera(String camera) { this.camera = camera; }
    public void setResolucaoDPI(double resolucaoDPI) { this.resolucaoDPI = resolucaoDPI; }
    public void setColorida(boolean colorida) { this.colorida = colorida; }
}
