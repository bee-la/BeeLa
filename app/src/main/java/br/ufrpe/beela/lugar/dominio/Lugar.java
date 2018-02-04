package br.ufrpe.beela.lugar.dominio;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;

/**
 * Created by vidal on 20/12/2017.
 */

public class Lugar implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private ArrayList<PerfilComida> comida;
    private ArrayList<PerfilMusica> musica;
    private ArrayList<PerfilEsporte> esporte;
    private ArrayList<PerfilLugar> lugar;
    private String localicao;
    private String caminho;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public ArrayList<PerfilComida> getComida() {
        return this.comida;
    }

    public ArrayList<PerfilMusica> getMusica() {
        return this.musica;
    }

    public ArrayList<PerfilEsporte> getEsporte() {
        return this.esporte;
    }

    public ArrayList<PerfilLugar> getPergLugar() {
        return this.lugar;
    }

    public String getLocalicao() {
        return localicao;
    }

    public String getCaminho() {return caminho;}

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescriacao(String descricao) {
        this.descricao = descricao;
    }

    public void setComida(ArrayList<PerfilComida> comida) {
        this.comida = comida;
    }

    public void setMusica(ArrayList<PerfilMusica> musica) {
        this.musica = musica;
    }

    public void setEsporte(ArrayList<PerfilEsporte> esporte) {
        this.esporte = esporte;
    }

    public void setPergLugar(ArrayList<PerfilLugar> lugar) {
        this.lugar = lugar;
    }

    public void setLocalicao(String localicao) {
        this.localicao = localicao;
    }

    public void setCaminho(String caminho) {this.caminho = caminho;}
}
