package br.ifsc.locadora;

public class ConsolePlataforma {
    private Console console;
    private Plataforma plataforma;
    private String compatibilidade;
    private double precoHora;

    public ConsolePlataforma(Console console, Plataforma plataforma, String compatibilidade, double precoHora) {
        this.console = console;
        this.plataforma = plataforma;
        this.compatibilidade = compatibilidade;
        this.precoHora = precoHora;
    }

    public Console getConsole() { return console; }
    public Plataforma getPlataforma() { return plataforma; }
    public double getPrecoHora() { return precoHora; }
}

