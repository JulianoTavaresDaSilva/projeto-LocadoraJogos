package br.ifsc.locadora;

public class LocacaoJogo {
    private int id;
    private TempoLocacaoJogo tempoLocacao;

    public LocacaoJogo(int id, TempoLocacaoJogo tempoLocacao) {
        this.id = id;
        this.tempoLocacao = tempoLocacao;
    }

    @Override
    public String toString() {
        return "Locação Jogo #" + id + " | " + tempoLocacao.toString();
    }
}
