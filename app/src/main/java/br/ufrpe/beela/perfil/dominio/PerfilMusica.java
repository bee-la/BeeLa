package br.ufrpe.beela.perfil.dominio;

/**
 * Created by vidal on 18/12/2017.
 */

public class PerfilMusica {
    private int id;
    private String nome;
    private String nome_perfil;
    private int id_lugar;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNome_perfil() {
        return nome_perfil;
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNome_perfil(String nome_perfil) {
        this.nome_perfil = nome_perfil;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
    }
}
