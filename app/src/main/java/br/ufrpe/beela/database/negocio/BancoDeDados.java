package br.ufrpe.beela.database.negocio;
import android.content.Context;

import java.util.ArrayList;

import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.HomeAct;

/**
 * Created by vidal on 29/12/2017.
 */

public class BancoDeDados {
    public void gerarLugares(Context context){
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        if(bd.inserirLugares()){
            criarRu(context);
            criarBailePerfumado(context);
            criarMirabilandia(context);
            criarShoppingRecife(context);
            criarRockRibs(context);
        }

    }
    private ArrayList<Lugar> lugaresPreferidos=new ArrayList<Lugar>();
    public void criarRu(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("RestauranteUniversitário");
        lugar.setDescriacao("Self-Service");
        lugar.setLocalicao("-8.014121,-34.951131");
        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        //
        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setId_lugar(lugar.getId());
        perfilComida.setNome("Self-Service");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setId_lugar(lugar.getId());
        perfilMusica.setNome("Variaveis");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);
    }

    public void criarRockRibs(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("RockRibs");
        lugar.setDescriacao("Restaurante");
        lugar.setLocalicao("-8.0640944,-34.8714444");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        //
        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setId_lugar(lugar.getId());
        perfilComida.setNome("Carnes");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Rock");
        perfilMusica.setId_lugar(lugar.getId());
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilMusica(perfilMusica);

    }

    public void criarShoppingRecife(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("ShoppingRecife");
        lugar.setDescriacao("ShoppingCenter");
        lugar.setLocalicao("-8.0640944,-34.8714444");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        String[] list = {"FastFood", "Orientais", "Massas"};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setId_lugar(lugar.getId());
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(perfilComida);
        }
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("NULL");
        perfilMusica.setId_lugar(lugar.getId());
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);
    }

    public void criarMirabilandia(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Mirabilandia");
        lugar.setDescriacao("ParquedeDiversoes");
        lugar.setLocalicao("-8.0326104,-34.875884");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        String[] list = {"FastFood", "Pizza"};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setId_lugar(lugar.getId());
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(perfilComida);
        }
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setId_lugar(lugar.getId());
        perfilMusica.setNome("Forro");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);

    }

    public void criarBailePerfumado(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("BailePerfumado");
        lugar.setDescriacao("CasaShow");
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
        perfilComida.setId_lugar(lugar.getId());
        perfilComida.setNome("Carnes");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setId_lugar(lugar.getId());
        perfilMusica.setNome("Sertanejo");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);

    }

}