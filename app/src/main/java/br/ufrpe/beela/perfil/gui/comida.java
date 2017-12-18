package br.ufrpe.beela.perfil.gui;

/**
 * Created by max on 18/12/17.
 */

public class comida {

    private String nome;

    public comida(String nome){

        this.nome = nome.toString();

    }

    public String getNome(){    return this.nome;   }

    @Override
    public String toString() {  return nome;    }
}
