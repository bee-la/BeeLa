package br.ufrpe.beela.usuario.dominio;

import java.io.Serializable;

/**
 * Created by max on 05/12/17.
 * Modificado em 29/12/2017.
 */

public class Usuario implements Serializable{
    private int Id;
    private String senha;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
