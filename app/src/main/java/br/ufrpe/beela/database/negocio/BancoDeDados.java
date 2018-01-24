package br.ufrpe.beela.database.negocio;
import android.content.Context;

import java.util.ArrayList;

import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.HomeAct;

/**
 * Created by vidal on 29/12/2017.
 */

public class BancoDeDados {
    private ArrayList<Lugar> lugaresPreferidos=new ArrayList<Lugar>();

    public void gerarLugares(Context context){
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        if(bd.inserirLugares()){
            criarRu(context);
            criarBailePerfumado(context);
            criarMirabilandia(context);
            criarShoppingRecife(context);
            criarRockRibs(context);
            criaParqueJaqueira(context);
//            criarFulano1(context);
//            criarFulano2(context);
//            criarFulano3(context);
//            criarFulano4(context);
//            criarFulano5(context);
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
        perfilComida.setId_lugar(lugar.getId());
        perfilComida.setNome("Self-Service");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setId_lugar(lugar.getId());
        perfilMusica.setNome("Variadas");
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);
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
            perfilComida.setId_lugar(lugar.getId());
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(perfilComida);
        }
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome("Rock");
        perfilMusica.setId_lugar(lugar.getId());
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);
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
            perfilComida.setId_lugar(lugar.getId());
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(perfilComida);
        }
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setId_lugar(lugar.getId());
        perfilMusica.setNome("Forró");
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(perfilMusica);

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

    public void criaParqueJaqueira(Context context){
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
            PerfilEsporte perfilEsporte = new PerfilEsporte();
            perfilEsporte.setId_lugar(lugar.getId());
            perfilEsporte.setNome(x);
            PerfilDAO bdp=new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarEsporte(perfilEsporte);
        }
    }

    public void criarFulano1(Context context){
        Pessoa pessoa = new Pessoa();
        pessoa.setCelular("987654321");
        pessoa.setNome("Fulano1");
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }
    public void criarFulano2(Context context){
        Pessoa pessoa = new Pessoa();
        pessoa.setCelular("997654321");
        pessoa.setNome("Fulano2");
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }
    public void criarFulano3(Context context){
        Pessoa pessoa = new Pessoa();
        pessoa.setCelular("927654321");
        pessoa.setNome("Fulano3");
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }
    public void criarFulano4(Context context){
        Pessoa pessoa = new Pessoa();
        pessoa.setCelular("187654321");
        pessoa.setNome("Fulano4");
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }
    public void criarFulano5(Context context){
        Pessoa pessoa = new Pessoa();
        pessoa.setCelular("687654321");
        pessoa.setNome("Fulano5");
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }


//TODO    Sem Funcionar

    public ArrayList<Pessoa> getListaContatos(Context contexto){
        PessoaDAO bd=new PessoaDAO();
        bd.getLer(contexto);
        ArrayList<Pessoa> listaPessoa=bd.getListaPessoa();
        return listaPessoa;
    }

    public ArrayList<Lugar> getLugares(){
        return lugaresPreferidos;
    }
}