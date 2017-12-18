package br.ufrpe.beela.perfil.gui;

/**
 * Created by max on 16/12/17.
 */

public class musica {

    private String nome;

    public musica(String nome){

        this.nome = nome.toString();
    }

    public String getNome(){

        return this.nome;    }

    @Override
    public String toString() {
        return nome;

    }
}
