package br.ufrpe.beela.lugar.negocio;

/**
 * Created by vidal on 12/02/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.usuario.dominio.Pessoa;

public class CriarMatriz {

    private static Lugar lugar;
    private static ArrayList<Lugar> listaLugares= new ArrayList<Lugar>();
   // private static ArrayList<Lugar> listaLugares=lugar.getListaLugares();

    private static Map<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();

    private static Pessoa pessoa;
    private static ArrayList<Pessoa> listaUsuario = new ArrayList<Pessoa>();
//    private static ArrayList<Pessoa> listaUsuario= pessoa.getListaUsuarios();


    public static void criaMatriz() {
        //matriz (Lugar, nota)

        HashMap<Lugar, Double> joao = new HashMap<>();
        HashMap<Lugar, Double> pedro = new HashMap<>();
        HashMap<Lugar, Double> ana = new HashMap<>();
        HashMap<Lugar, Double> maria = new HashMap<>();
        HashMap<Lugar, Double> jose = new HashMap<>();
        HashMap<Lugar, Double> luiz = new HashMap<>();


        //lugar			   //nota que Joao deu a esse lugar
        joao.put(listaLugares.get(0), 0.86);
        joao.put(listaLugares.get(1), 0.7);
        joao.put(listaLugares.get(2), 0.7);
        joao.put(listaLugares.get(3), 0.7);


        //lugar		     //nota que Pedro deu a esse lugar
        pedro.put(listaLugares.get(1), 0.5);
        pedro.put(listaLugares.get(2), 0.87);
        pedro.put(listaLugares.get(3), 0.5);


        //lugar		   //nota que Ana deu a esse lugar
        ana.put(listaLugares.get(4), 0.8);
        ana.put(listaLugares.get(5), 0.46);
        ana.put(listaLugares.get(6), 0.7);
        ana.put(listaLugares.get(7), 0.3);
        ana.put(listaLugares.get(8), 0.1);


        //lugar		     //nota que Maria deu a esse lugar
        maria.put(listaLugares.get(3), 0.3);
        maria.put(listaLugares.get(4), 0.4);
        maria.put(listaLugares.get(5), 0.5);
        maria.put(listaLugares.get(6), 0.6);


        //lugar		     //nota que Jose deu a esse lugar
        jose.put(listaLugares.get(7), 0.1);
        jose.put(listaLugares.get(8), 0.1);
        jose.put(listaLugares.get(9), 0.1);
        jose.put(listaLugares.get(5), 0.1);


        //lugar		    //nota que Luiz deu a esse lugar
        luiz.put(listaLugares.get(7), 0.7);
        luiz.put(listaLugares.get(8), 0.8);
        luiz.put(listaLugares.get(9), 0.9);


        //Usuario         //Matriz(Lugar, nota)
        matrizTotal.put(listaUsuario.get(0), 		joao		);
        matrizTotal.put(listaUsuario.get(1), 		pedro		);
        matrizTotal.put(listaUsuario.get(2), 		ana			);
        matrizTotal.put(listaUsuario.get(3), 		maria		);
        matrizTotal.put(listaUsuario.get(4), 		jose		);
        matrizTotal.put(listaUsuario.get(5), 		luiz		);
    }

    //	Map<Usuario, HashMap<Lugar, Double>>
    public static Map<Pessoa, HashMap<Lugar, Double>> getMatriz(){
        criaMatriz();
        return matrizTotal;
    }

}

