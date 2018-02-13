package br.ufrpe.beela.lugar.negocio;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.usuario.dominio.Pessoa;


public class SlopeOne {

    private static Map<Integer, Map<Integer, Double>> matrizDeDiferenca = new HashMap<>();
    private static Map<Integer, Map<Integer, Integer>> matrizDeFrequencia = new HashMap<>();
    private static Map<Pessoa, HashMap<Integer, Double>> dadosDeSaida = new HashMap<>();

//    private static CriarMatriz cria;
//    private static Map<Pessoa, HashMap<Lugar, Double>> matrizInicial =cria.getMatriz();

    private static Map<Pessoa, HashMap<Integer, Double>> matrizInicial;

    private static ArrayList<Integer> listaLugares= new ArrayList<Integer>();

    public SlopeOne(Map<Pessoa, HashMap<Integer, Double>> matriz){
        this.matrizInicial=matriz;
    }

    public static void slopeOne() {
        buildDifferencesMatrix(matrizInicial);

//        System.out.println("");
//        System.out.println("\nSlope One - com Previsões:\n");
        predict(matrizInicial);

    }


    /**
     * Com base nos dados disponíveis, é calculado as relações entre os
          * usuários e número de ocorrências dos lugares
     */

    private static void buildDifferencesMatrix(Map<Pessoa, HashMap<Integer, Double>> data) {
        for (HashMap<Integer, Double> user : data.values()) {
            for (Entry<Integer, Double> e : user.entrySet()) {
                if (!matrizDeDiferenca.containsKey(e.getKey())) {
                    matrizDeDiferenca.put(e.getKey(), new HashMap<Integer, Double>());
                    matrizDeFrequencia.put(e.getKey(), new HashMap<Integer, Integer>());
                }

                for (Entry<Integer, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (matrizDeFrequencia.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = matrizDeFrequencia.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (matrizDeDiferenca.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = matrizDeDiferenca.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    matrizDeFrequencia.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    matrizDeDiferenca.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }

        for (Integer j : matrizDeDiferenca.keySet()) {
            for (Integer i : matrizDeDiferenca.get(j).keySet()) {
                double oldValue = matrizDeDiferenca.get(j).get(i).doubleValue();
                int count = matrizDeFrequencia.get(j).get(i).intValue();
                matrizDeDiferenca.get(j).put(i, oldValue / count);
            }
        }
//        printData(data);
    }



    /**
     * Com base nos dados existentes, prevê todas as classificações faltantes.
          * São dados de usuários existentes e classificações de seus lugares.
     */

    private static void predict(Map<Pessoa, HashMap<Integer, Double>> data) {
        HashMap<Integer, Double> uPred = new HashMap<Integer, Double>();
        HashMap<Integer, Integer> uFreq = new HashMap<Integer, Integer>();
        for (Integer j : matrizDeDiferenca.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }

        for (Entry<Pessoa, HashMap<Integer, Double>> e : data.entrySet()) {
            for (Integer j : e.getValue().keySet()) {
                for (Integer k : matrizDeDiferenca.keySet()) {
                    try {
                        double predictedValue = matrizDeDiferenca.get(k).get(j).doubleValue() + e.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * matrizDeFrequencia.get(k).get(j).intValue();
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + matrizDeFrequencia.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }


            HashMap<Integer, Double> clean = new HashMap<Integer, Double>();
            for (Integer j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j).doubleValue() / uFreq.get(j).intValue());
                }
            }
            for (Integer j : listaLugares) {
                if (e.getValue().containsKey(j)) {
                    clean.put(j, e.getValue().get(j));
                }


// 				 Estava sempre caindo nesse else e retornando -1
//                else {
//                    clean.put(j, -1.0);
//                }


            }
            dadosDeSaida.put(e.getKey(), clean);
        }
//        printData(dadosDeSaida);
    }

    private static void printData(Map<Pessoa, HashMap<Lugar, Double>> data) {
        for (Pessoa pessoa : data.keySet()) {
            System.out.println(pessoa.getNome() + ":");
//            print(data.get(pessoa));
            System.out.println("");
        }
    }

    private static void print(HashMap<Lugar, Double> hashMap) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (Lugar j : hashMap.keySet()) {
            System.out.println(" " + j.getNome() + " --> " + formatter.format(hashMap.get(j).doubleValue()));
        }
    }

}
