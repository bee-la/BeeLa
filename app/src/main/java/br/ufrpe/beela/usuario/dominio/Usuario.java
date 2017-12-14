package br.ufrpe.beela.usuario.dominio;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

/**
 * Created by max on 05/12/17.
 */

public class Usuario {
    private int Id = 0;
    private String nome;
    private String senha;
    private String email;
    private String celular;
    PerfilUsuario perfil = new PerfilUsuario();

    public PerfilUsuario getPerfil(){return perfil;}
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public int getId() {
        return Id;
    }
    public String getCelular() {
        return celular;
    }

    public void setPerfil(PerfilUsuario perfil){this.perfil = perfil;}
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public void setId(int id) {
        Id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){return nome;}

}
