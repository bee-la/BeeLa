package br.ufrpe.beela.perfil.dominio;

/**
 * Created by Anderson on 29/12/2017.
 */

public class PerfilEsporte {
    private int id;
    private String nome;
    private int id_usuario;
    private String nome_perfil;
    private int id_lugar;

    public int getId(){return id;}
    public String getNome() {return nome;}
    public int getId_usuario() {return id_usuario;}
    public String getNome_perfil() {return nome_perfil;}
    public  int getId_lugar(){return id_lugar;}

    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}
    public void setNome_perfil(String nome_perfil) {this.nome_perfil = nome_perfil;}
    public void setId_lugar(int id_lugar){this.id_lugar=id_lugar;}
}