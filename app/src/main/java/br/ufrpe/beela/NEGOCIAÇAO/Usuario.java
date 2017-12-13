package br.ufrpe.beela.NEGOCIAÇAO;

import br.ufrpe.beela.GUI.Perfil;

/**
 * Created by max on 05/12/17.
 */

public class Usuario {
    private int Id = 0;
    private String nome;
    private String senha;
    private String email;
    private String celular;
    Perfil perfil = new Perfil();

    public void setPerfil(Perfil perfil){this.perfil = perfil;}
    public Perfil getPerfil(){return perfil;}

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){

        return nome;

    }

}
