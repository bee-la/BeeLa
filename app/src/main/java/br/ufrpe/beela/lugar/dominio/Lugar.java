package br.ufrpe.beela.lugar.dominio;

import com.google.android.gms.maps.model.LatLng;

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
    private String localicao;
    private String caminho;
    private String texto;
    private Double notaGeral = 0.0;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalicao() {
        return localicao;
    }

    public String getCaminho() {return caminho;}

    public String getTexto(){
        return this.texto;
    }

    public Double getNotaGeral() {return notaGeral;}

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescriacao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocalicao(String localicao) {
        this.localicao = localicao;
    }

    public void setCaminho(String caminho) {this.caminho = caminho;}

    public void setTexto(String texto){
        this.texto=texto;
    }

    public LatLng getLatLng() {
        String destino[] = localicao.split(",");
        Double a  = Double.parseDouble(destino[0]);
        Double b = Double.parseDouble(destino[1]);
        LatLng c = new LatLng(a,b);

        return c;
    }

    public void setNotaGeral(Double notaGeral) {this.notaGeral = notaGeral;}

}
