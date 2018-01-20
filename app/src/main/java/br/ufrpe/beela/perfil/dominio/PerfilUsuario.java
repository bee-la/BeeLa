package br.ufrpe.beela.perfil.dominio;

import java.util.ArrayList;

/**
 * Created by vidal on 13/12/2017.
 */

public class PerfilUsuario {
    private int id;
    private int id_usuario;
    private String nome;
    private ArrayList<PerfilComida> comida;
    private ArrayList<PerfilMusica> musica;
    private ArrayList<PerfilEsporte> esporte;
    private boolean selecionado;

    public int getId(){return this.id;}
    public int getId_Usuario(){return this.id_usuario;}
    public String getNome(){return this.nome;}
    public ArrayList<PerfilComida> getComida(){return this.comida;}
    public ArrayList<PerfilMusica> getMusica(){return this.musica;}
    public ArrayList<PerfilEsporte> getEsporte(){return this.esporte;}


    public void setId(int id){this.id = id;}
    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}
    public void setNome(String nome){this.nome = nome;}
    public void setComida(ArrayList<PerfilComida> comida){this.comida = comida;}
    public void setMusica(ArrayList<PerfilMusica> musica){this.musica = musica;}
    public void setEsporte(ArrayList<PerfilEsporte> esporte){this.esporte = esporte;}


    public boolean isSelecionado(){
        return selecionado;
    }
    public void setSelecionado(boolean selecionado){
        this.selecionado=selecionado;
    }


}
