package br.ifsc.locadora;

import java.util.List;

public class LocacaoConsole {
    private int id;
    private double valorTotal;
    private Usuario usuario;
    private Console console;
    private List<Acessorio> acessorios;
    private TempoLocacaoConsole tempoLocacao;

    public LocacaoConsole(int id, Usuario usuario, Console console, List<Acessorio> acessorios, int horas) {
        this.id = id;
        this.usuario = usuario;
        this.console = console;
        this.acessorios = acessorios;
        this.tempoLocacao = new TempoLocacaoConsole(id, horas, this);
        this.valorTotal = calcularValor();
    }

    private double calcularValor() {
        double valor = console.getPrecoHora() * tempoLocacao.getHoras();
        valor += acessorios.size() * 5.0;
        return valor;
    }

    @Override
    public String toString() {
        return "Locação Console #" + id + " | " + usuario.getNome() +
                " - " + console.getNome() + " (" + tempoLocacao.getHoras() + "h)" +
                " | Acessórios: " + acessorios.size() +
                " | Valor: R$" + valorTotal;
    }
}
