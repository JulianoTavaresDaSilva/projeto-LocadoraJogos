package br.ifsc.locadora;

import java.time.LocalDate;

public class TempoLocacaoJogo {
    private Usuario usuario;
    private JogoPlataforma jogoPlataforma;
    private LocalDate dataInicio;
    private int dias;
    private double valorTotal;

    public TempoLocacaoJogo(Usuario usuario, JogoPlataforma jogoPlataforma, int dias) {
        this.usuario = usuario;
        this.jogoPlataforma = jogoPlataforma;
        this.dias = dias;
        this.dataInicio = LocalDate.now();
        this.valorTotal = calcularValor();
    }

    private double calcularValor() {
        return jogoPlataforma.getPrecoDiario() * dias;
    }

    public Usuario getUsuario() { return usuario; }
    public JogoPlataforma getJogoPlataforma() { return jogoPlataforma; }
    public LocalDate getDataInicio() { return dataInicio; }
    public int getDias() { return dias; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return usuario.getNome() + " alugou " + jogoPlataforma.getJogo().getTitulo() +
                " por " + dias + " dias | Valor total: R$" + valorTotal;
    }
}
