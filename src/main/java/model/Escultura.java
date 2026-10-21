package model;

import java.time.LocalDate;

public class Escultura extends Obra
{
    // Atributos
    private String Material;
    private double pesoKG;

    // Construtores

    public Escultura(String nome, String artista, String descricao, LocalDate dataCriacao, String material, double pesoKG) {
        super(nome, artista, descricao, dataCriacao);
        Material = material;
        this.pesoKG = pesoKG;
    }

    public Escultura() {}

    // Acessores
    public String getMaterial() { return Material; }
    public double getPesoKG() { return pesoKG; }

    public void setMaterial(String material) { Material = material; }
    public void setPesoKG(double pesoKG) { this.pesoKG = pesoKG; }
}
