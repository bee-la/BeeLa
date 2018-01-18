package br.ufrpe.beela.perfil.dominio;

import java.util.ArrayList;

/**
 * Created by vidal on 18/01/2018.
 */

public class PerfilLugar{
    private int id;
    private int id_lugar;
    private ArrayList<PerfilComida> comida;
    private ArrayList<PerfilMusica> musica;
    private ArrayList<PerfilEsporte> esporte;
    private String localicao;

    public int getId(){return this.id;}
    public int getId_Lugar(){return this.id_lugar;}
    public ArrayList<PerfilComida> getComida(){return this.comida;}
    public ArrayList<PerfilMusica> getMusica(){return this.musica;}
    public ArrayList<PerfilEsporte> getEsporte(){return this.esporte;}
    public String getLocalicao() {return localicao;}

    public void setId(int id){this.id = id;}
    public void setId_Lugar(int id_Lugar) {this.id_lugar = id_lugar;}
    public void setComida(ArrayList<PerfilComida> comida){this.comida = comida;}
    public void setMusica(ArrayList<PerfilMusica> musica){this.musica = musica;}
    public void setEsporte(ArrayList<PerfilEsporte> esporte){this.esporte = esporte;}
    public void setLocalicao(String localicao) {this.localicao = localicao;}

}
