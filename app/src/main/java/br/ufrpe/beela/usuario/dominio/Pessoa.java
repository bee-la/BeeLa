package br.ufrpe.beela.usuario.dominio;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

/**
 * Created by vidal on 29/12/2017.
 */

public class Pessoa {
    private int id;
    private String nome;
    private String celular;
    private int id_usuario;
    private PerfilUsuario perfil = new PerfilUsuario();


    public int getId(){return id;}
    public String getNome() {return nome;}
    public String getCelular() {return celular;}
    public PerfilUsuario getPerfil(){return perfil;}
    public int getId_usuario() {return id_usuario;}

    public void setId(int id){this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setCelular(String celular) {this.celular = celular;}
    public void setPerfil(PerfilUsuario perfil){this.perfil = perfil;}
    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}
}
