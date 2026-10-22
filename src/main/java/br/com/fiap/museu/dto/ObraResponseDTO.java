package br.com.fiap.museu.dto;

public class ObraResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private int ano;
    private String tipo;
    private String detalhesEspecificos;
    private int votos;

    public ObraResponseDTO(Long id, String titulo, String autor, int ano, String tipo, String detalhesEspecificos, int votos) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.tipo = tipo;
        this.detalhesEspecificos = detalhesEspecificos;
        this.votos = votos;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAno() { return ano; }
    public String getTipo() { return tipo; }
    public String getDetalhesEspecificos() { return detalhesEspecificos; }
    public int getVotos() { return votos; }
}