package com.sd.prj_mvc_model.objetos;

public class Usuarios {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String papel;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        if ("Administrador".equals(papel) || "Usuário".equals(papel)) {
            this.papel = papel;
        } else {
            throw new IllegalArgumentException("Papel inválido: " + papel);
        }
    }

    // toString para depuração
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", papel='" + papel + '\'' +
                '}';
    }
    
}
