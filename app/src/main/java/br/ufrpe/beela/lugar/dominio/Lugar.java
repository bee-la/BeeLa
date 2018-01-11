package br.ufrpe.beela.lugar.dominio;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;

/**
 * Created by vidal on 20/12/2017.
 */

public class Lugar {

    private int id;
    private ArrayList<PerfilMusica> perfilMusicas;
    private ArrayList<PerfilComida> perfilComidas;
    private String nome;
    private String localicao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public ArrayList<PerfilMusica> getPerfilMusicas() {
        return perfilMusicas;

    }

    public void setPerfilMusicas(ArrayList<PerfilMusica> perfilMusicas) {
        this.perfilMusicas = perfilMusicas;
    }

    public ArrayList<PerfilComida> getPerfilComidas() {
        return perfilComidas;

    }

    public void setPerfilComidas(ArrayList<PerfilComida> perfilComidas) {
        this.perfilComidas = perfilComidas;
    }

    public String getNome() {
        return nome;

    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getLocalicao() {
        return localicao;

    }

    public void setLocalicao(String localicao) {
        this.localicao = localicao;

    }

}
