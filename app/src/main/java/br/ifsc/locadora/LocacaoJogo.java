package br.ifsc.locadora;

import java.time.LocalDate;

public class LocacaoJogo {
    private int id;
    private LocalDate dataInicio;
    private double valorTotal;
    private Usuario usuario;
    private JogoPlataforma jogoPlataforma;
    private TempoLocacaoJogo tempoLocacao; // mudou o nome para ficar mais claro

    public LocacaoJogo(int id, Usuario usuario, JogoPlataforma jogoPlataforma, int dias) {
        this.id = id;
        this.usuario = usuario;
        this.jogoPlataforma = jogoPlataforma;
        this.dataInicio = LocalDate.now();

        this.tempoLocacao = new TempoLocacaoJogo(id, dias, this);
        this.valorTotal = dias * jogoPlataforma.getPrecoDiario();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public TempoLocacaoJogo getTempoLocacao() {
        return tempoLocacao;
    }

    @Override
    public String toString() {
        return "Locação Jogo #" + id + " | " + usuario.getNome() +
                " alugou " + jogoPlataforma.getJogo().getTitulo() +
                " por " + tempoLocacao.getDias() + " dias | Valor: R$ " + valorTotal;
    }
}
