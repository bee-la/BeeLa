package br.ufrpe.beela.database.negocio;

import android.content.Context;

import java.util.ArrayList;

import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;

/**
 * Created by vidal on 29/12/2017.
 */

public class AlimentarBancoDeDados {
    private ArrayList<Lugar> lugaresPreferidos = new ArrayList<Lugar>();

    public void gerarLugares(Context context) {
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        if (bd.inserirLugares()) {
            criarRu(context);
            criarBailePerfumado(context);
            criarMirabilandia(context);
            criarShoppingRecife(context);
            criarRockRibs(context);
            criaParqueJaqueira(context);
            criarIgrejaDoCarmmo(context);
        }

    }

    public void criarRu(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("RU - UFRPE");
        lugar.setDescriacao("Self-Service");
        lugar.setLocalicao("-8.014121,-34.951131");
        lugaresPreferidos.add(lugar);
        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        //
        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome("Self-Service");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Variadas");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criarIgrejaDoCarmmo(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Igreja Do Carmmo");
        lugar.setDescriacao("Igreja");
        lugar.setLocalicao("-8.0168458,-34.849372");
        lugaresPreferidos.add(lugar);
        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        //
        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome("Null");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Padre Marcelo Rossi");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
        //
        PerfilLugar perfilLugar = new PerfilLugar();
        perfilLugar.setNome("Igreja");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarParaLugares(lugar.getId(),perfilLugar);
    }

    public void criarRockRibs(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("RockRibs");
        lugar.setDescriacao("Restaurante");
        lugar.setLocalicao("-8.0640944,-34.8714444");
        lugaresPreferidos.add(lugar);

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        //
        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome("Carnes");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Rock");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }

    public void criarShoppingRecife(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Shopping Recife");
        lugar.setDescriacao("Shopping Center");
        lugar.setLocalicao("-8.0640944,-34.8714444");
        lugaresPreferidos.add(lugar);
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        String[] list = {"FastFood", "Orientais", "Pizza"};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        }
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Rock");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criarMirabilandia(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Mirabilandia");
        lugar.setDescriacao("Parque de Diversões");
        lugar.setLocalicao("-8.0326104,-34.875884");
        lugaresPreferidos.add(lugar);

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        String[] list = {"FastFood", "Pizza"};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        }
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Forró");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }

    public void criarBailePerfumado(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Baile Perfumado");
        lugar.setDescriacao("Casa de Show");
        lugar.setLocalicao("-8.0592289,-34.9145652");
        lugaresPreferidos.add(lugar);
        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome("Carnes");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Sertanejo");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criaParqueJaqueira(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Parque Jaqueira");
        lugar.setDescriacao("Parque");
        lugar.setLocalicao("-8.0374702,-34.9746089");
        lugaresPreferidos.add(lugar);
        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        String[] list = {"Caminhada", "Corrida", "Ciclismo"};
        for (String x : list) {
            PerfilEsporte perfilEsporte = new PerfilEsporte();;
            perfilEsporte.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarEsporte(lugar.getId(),perfilEsporte);
        }
    }

//TODO    Sem Funcionar
}