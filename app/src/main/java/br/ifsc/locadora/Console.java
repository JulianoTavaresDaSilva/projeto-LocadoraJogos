package br.ifsc.locadora;

public class Console {
    private int id;
    private String nome;
    private double precoHora;

    public Console(int id, String nome, double precoHora) {
        this.id = id;
        this.nome = nome;
        this.precoHora = precoHora;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPrecoHora() { return precoHora; }

    @Override
    public String toString() {
        return nome + " - R$" + precoHora + "/hora";
    }
}

