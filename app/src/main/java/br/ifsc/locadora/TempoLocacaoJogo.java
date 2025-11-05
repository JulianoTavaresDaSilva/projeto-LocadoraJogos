package br.ifsc.locadora;

public class TempoLocacaoJogo {
    private int id;
    private int dias;
    private LocacaoJogo locacaoJogo;

    public TempoLocacaoJogo(int id, int dias, LocacaoJogo locacaoJogo) {
        this.id = id;
        this.dias = dias;
        this.locacaoJogo = locacaoJogo;
    }

    public int getId() {
        return id;
    }

    public int getDias() {
        return dias;
    }

    public LocacaoJogo getLocacaoJogo() {
        return locacaoJogo;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setLocacaoJogo(LocacaoJogo locacaoJogo) {
        this.locacaoJogo = locacaoJogo;
    }

    @Override
    public String toString() {
        return "Tempo de Locação Jogo #" + id + " - " + dias + " dias";
    }
}
