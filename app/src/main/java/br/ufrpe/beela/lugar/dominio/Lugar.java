package br.ufrpe.beela.lugar.dominio;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;

/**
 * Created by vidal on 20/12/2017.
 */

public class Lugar {

    private int id;
    private String nome;
    private String descricao;
    private ArrayList<PerfilComida> comida;
    private ArrayList<PerfilMusica> musica;
    private ArrayList<PerfilEsporte> esporte;
    private String localicao;


    public int getId() {
        return id;
    }
    public String getNome() {return nome;}
    public String getDescricao() {return descricao;}
    public ArrayList<PerfilComida> getComida(){return this.comida;}
    public ArrayList<PerfilMusica> getMusica(){return this.musica;}
    public ArrayList<PerfilEsporte> getEsporte(){return this.esporte;}
    public String getLocalicao() {return localicao;}


    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setDescriacao(String descricao) {this.descricao = descricao;}
    public void setComida(ArrayList<PerfilComida> comida){this.comida = comida;}
    public void setMusica(ArrayList<PerfilMusica> musica){this.musica = musica;}
    public void setEsporte(ArrayList<PerfilEsporte> esporte){this.esporte = esporte;}
    public void setLocalicao(String localicao) {this.localicao = localicao;}


}
