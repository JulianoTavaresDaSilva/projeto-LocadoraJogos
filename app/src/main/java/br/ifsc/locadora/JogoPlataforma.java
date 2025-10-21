package br.ifsc.locadora;

public class JogoPlataforma {
    private Jogo jogo;
    private Plataforma plataforma;
    private int estoque;
    private double precoDiario;

    public JogoPlataforma(Jogo jogo, Plataforma plataforma, int estoque, double precoDiario) {
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.estoque = estoque;
        this.precoDiario = precoDiario;
    }

    public Jogo getJogo() { return jogo; }
    public Plataforma getPlataforma() { return plataforma; }
    public int getEstoque() { return estoque; }
    public double getPrecoDiario() { return precoDiario; }

    public void diminuirEstoque() {
        if (estoque > 0) estoque--;
    }

    @Override
    public String toString() {
        return jogo.getTitulo() + " - " + plataforma.getNome() + " | R$ " + precoDiario + "/dia | Estoque: " + estoque;
    }
}

