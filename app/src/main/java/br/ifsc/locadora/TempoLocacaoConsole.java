package br.ifsc.locadora;

import java.util.List;

public class TempoLocacaoConsole {
    private Usuario usuario;
    private Console console;
    private List<Acessorio> acessorios;
    private int horas;
    private double valorTotal;

    public TempoLocacaoConsole(Usuario usuario, Console console, List<Acessorio> acessorios, int horas) {
        this.usuario = usuario;
        this.console = console;
        this.acessorios = acessorios;
        this.horas = horas;
        this.valorTotal = calcularValor();
    }

    private double calcularValor() {
        double valor = console.getPrecoHora() * horas;
        valor += acessorios.size() * 5.0;
        return valor;
    }

    public Usuario getUsuario() { return usuario; }
    public Console getConsole() { return console; }
    public List<Acessorio> getAcessorios() { return acessorios; }
    public int getHoras() { return horas; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return usuario.getNome() + " alugou " + console.getNome() +
                " por " + horas + " horas | Acessórios: " + acessorios.size() +
                " | Valor total: R$" + valorTotal;
    }
}
