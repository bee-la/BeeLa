package br.ufrpe.beela.cachorro.dominio;

public class Cachorro {
    private int id;
    private int idPessoa;
    private String nome;
    private String raca;
    private String cor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Cachorro(int id,int idPessoa, String nome, String raca, String cor) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.raca = raca;
        this.cor = cor;
    }

    public Cachorro() {
    }

    private boolean selecionado;

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

}


