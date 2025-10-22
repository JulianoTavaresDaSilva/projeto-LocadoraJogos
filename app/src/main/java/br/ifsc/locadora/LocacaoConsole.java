package br.ifsc.locadora;

import java.util.List;

public class LocacaoConsole {
    private int id;
    private double horaInicio;
    private double horaFim;
    private double valorTotal;
    private Usuario usuario;
    private Console console;
    private List<Acessorio> acessorios;
    private TempoLocacaoConsole tempo;

    public LocacaoConsole(int id, Usuario usuario, Console console, List<Acessorio> acessorios, TempoLocacaoConsole tempo) {
        this.id = id;
        this.usuario = usuario;
        this.console = console;
        this.acessorios = acessorios;
        this.tempo = tempo;
        this.horaInicio = 0;
        this.horaFim = tempo.getHoras();
        this.valorTotal = calcularValor();
    }

    private double calcularValor() {
        double valor = console.getPrecoHora() * tempo.getHoras();
        valor += acessorios.size() * 5.0;
        return valor;
    }

    @Override
    public String toString() {
        return "Locação Console #" + id + " | " + usuario.getNome() +
                " - " + console.getNome() + " (" + tempo.getHoras() + "h)" +
                " | Acessórios: " + acessorios.size() +
                " | Valor: R$" + valorTotal;
    }
}

