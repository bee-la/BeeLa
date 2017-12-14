package br.ufrpe.beela.perfil.dominio;

import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by vidal on 13/12/2017.
 */

public class PerfilUsuario {
    private int id;
    private int id_usuario;
    private String nome = "";
    private String comida;
    private String musica;
    private String esporte;

    public int getId_Usuario(){return this.id_usuario;}
    public String getNome(){return this.nome;}
    public String getComida(){return this.comida;}
    public String getMusica(){return this.musica;}
    public String getEsporte(){return this.esporte;}

    public void setId(int id){this.id = id;}
    public void setId_usuario(int id_usuario) {this.id_usuario = LoginAct.usuario.getId();}
    public void setNome(String nome){this.nome = nome;}
    public void setComida(String comida){this.comida = comida;}
    public void setMusica(String musica){this.musica = musica;}
    public void setEsporte(String esporte){this.esporte = esporte;}

}
