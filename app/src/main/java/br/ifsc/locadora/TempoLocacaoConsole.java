package br.ifsc.locadora;

public class TempoLocacaoConsole {
    private int id;
    private int horas;
    private LocacaoConsole locacaoConsole;

    public TempoLocacaoConsole(int id, int horas, LocacaoConsole locacaoConsole) {
        this.id = id;
        this.horas = horas;
        this.locacaoConsole = locacaoConsole;
    }

    public int getId() {
        return id;
    }

    public int getHoras() {
        return horas;
    }

    public LocacaoConsole getLocacaoConsole() {
        return locacaoConsole;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setLocacaoConsole(LocacaoConsole locacaoConsole) {
        this.locacaoConsole = locacaoConsole;
    }

    @Override
    public String toString() {
        return "Tempo de Locação Console #" + id + " - " + horas + "h";
    }
}
