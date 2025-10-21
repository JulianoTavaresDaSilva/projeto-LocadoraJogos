package br.ifsc.locadora;

public class Jogo {
    private int id;
    private String titulo;
    private String genero;

    public Jogo(int id, String titulo, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public String toString() {
        return titulo + " (" + genero + ")";
    }
}

