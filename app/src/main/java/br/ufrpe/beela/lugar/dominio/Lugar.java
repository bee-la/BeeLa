package br.ufrpe.beela.lugar.dominio;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;

/**
 * Created by vidal on 20/12/2017.
 */

public class Lugar {

    private int id;
    private String nome;
    private String descricao;
    private ArrayList<PerfilLugar> perfilLugar = new ArrayList<>();

    public int getId() {
        return id;
    }
    public String getNome() {return nome;}
    public String getDescricao() {return descricao;}
    public ArrayList<PerfilLugar> getPerfilLugar() {return perfilLugar;}

    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setDescriacao(String descricao) {this.descricao = descricao;}
    public void setPerfilLugar(ArrayList<PerfilLugar> perfilLugar) {this.perfilLugar = perfilLugar;}

}
