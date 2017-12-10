package br.rodriguesufrpe.anderson.beela;

/**
 * Created by Anderson on 09/12/2017.
 */

public class PerguntasRespostas {

    private String perguntas[]={ "O que você quer comer hoje?", "O que você quer ouvir hoje?" };

    private String comidas[]={"Massa", "Oriental","Carnes", "Vegetariano"};
    private String massas[]={"Lasanha","Pizza", "Creperia", "Rodízio de massas"};
    private String oriental[]={"Temakeria", "Sushi", "Yakisoba", "Rodízio de sushi"};
    private String carnes[]={"Churrascaria", "Hamburgueria", "Fast Food","Rodízio de carne"};

    public String[] getPerguntas(){
        return perguntas;    }

    public String[] getComidas(){
        return comidas;    }

    public String[] getMassas(){
        return massas;    }

    public String[] getOriental(){
        return oriental;    }

    public String[] getCarnes(){
        return carnes;    }
}
