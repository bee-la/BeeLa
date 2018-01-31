package br.ufrpe.beela.lugar.dominio;

/**
 * Created by max on 28/01/18.
 */

public class Endereco {

    private String bairro;
    private String cidade;
    private String rua;
    private String estado;


    public Endereco(String bairro, String cidade, String rua, String estado){

        this.bairro = this.bairro;
        this.cidade = this.cidade;
        this.rua = this.rua;
        this.estado = this.estado;

    }


    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }






}
