package br.ufrpe.beela.perfil.dominio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vidal on 13/12/2017.
 */

public class PerfilUsuario implements Serializable {
    private int id;
    private String nome;
    private ArrayList<PerfilComida> comida;
    private ArrayList<PerfilMusica> musica;
    private ArrayList<PerfilEsporte> esporte;
    private ArrayList<PerfilLugar> lugar;

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
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

    public ArrayList<PerfilLugar> getLugar() {
        return this.lugar;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setLugar(ArrayList<PerfilLugar> lugar) {
        this.lugar = lugar;
    }

    private boolean selecionado;

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
