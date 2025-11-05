package br.ifsc.locadora;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private boolean administrador;

    public Usuario(int id, String nome, String email, String telefone, String senha, boolean administrador) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.administrador = administrador;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getSenha() { return senha; }
    public boolean isAdministrador() { return administrador; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setAdministrador(boolean administrador) { this.administrador = administrador; }

    @Override
    public String toString() {
        String tipo = administrador ? "Administrador" : "Cliente";
        return "Usuário: " + nome + " (" + email + ") - " + tipo;
    }
}
