package br.ifsc.locadora;

public class LocacaoConsole {
    private int id;
    private TempoLocacaoConsole tempoLocacao;

    public LocacaoConsole(int id, TempoLocacaoConsole tempoLocacao) {
        this.id = id;
        this.tempoLocacao = tempoLocacao;
    }

    @Override
    public String toString() {
        return "Locação Console #" + id + " | " + tempoLocacao.toString();
    }
}
