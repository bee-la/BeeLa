package br.ufrpe.beela.usuario.dominio;

import java.util.ArrayList;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

/**
 * Created by vidal on 29/12/2017.
 */

public class Pessoa {

    private int id;
    private String nome;
    private String celular;
    private Usuario usuario = new Usuario();
    private ArrayList<PerfilUsuario> perfilUsuarioArrayList = new ArrayList<PerfilUsuario>();
    private PerfilUsuario perfilAtual = new PerfilUsuario();


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public PerfilUsuario getPerfilAtual() {
        return perfilAtual;
    }

    public ArrayList<PerfilUsuario> getPerfilUsuarioArrayList() {return perfilUsuarioArrayList;}

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPerfilAtual(PerfilUsuario perfilAtual) {
        this.perfilAtual = perfilAtual;
    }

    public void setPerfilUsuarioArrayList(ArrayList<PerfilUsuario> perfilUsuarioArrayList) {this.perfilUsuarioArrayList = perfilUsuarioArrayList;}

    private boolean selecionado;
    public boolean isSelecionado() {return selecionado;}

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
