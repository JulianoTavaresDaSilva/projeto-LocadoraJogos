package br.ifsc.locadora;

import java.time.LocalDate;

public class LocacaoJogo {
    private int id;
    private LocalDate dataInicio;
    private double valorTotal;
    private Usuario usuario;
    private JogoPlataforma jogoPlataforma;
    private TempoLocacaoJogo tempo;

    public LocacaoJogo(int id, Usuario usuario, JogoPlataforma jogoPlataforma, TempoLocacaoJogo tempo) {
        this.id = id;
        this.usuario = usuario;
        this.jogoPlataforma = jogoPlataforma;
        this.tempo = tempo;
        this.dataInicio = LocalDate.now();
        this.valorTotal = tempo.getDias() * jogoPlataforma.getPrecoDiario();
    }

    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return "Locacao #" + id + " | " + usuario.getNome() + " alugou " +
                jogoPlataforma.getJogo().getTitulo() + " por " + tempo.getDias() +
                " dias | Valor: R$ " + valorTotal;
    }
}
