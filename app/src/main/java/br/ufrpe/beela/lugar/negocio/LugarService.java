package br.ufrpe.beela.lugar.negocio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by vidal on 18/01/2018.
 */

public class LugarService {
    public ArrayList<Lugar> gerarListaLugar(PerfilUsuario perfilUsuario, Context context) {
        ArrayList<Lugar> listLugar = new ArrayList<Lugar>();
        ArrayList<Integer> listId = new ArrayList<Integer>();

        try {
            for (PerfilComida comida : perfilUsuario.getComida()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(comida);
            }
            for (PerfilMusica musica : perfilUsuario.getMusica()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(listId, musica);
            }
            for (PerfilEsporte esporte : perfilUsuario.getEsporte()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(listId, esporte);
            }
            for (PerfilLugar lugar : perfilUsuario.getLugar()) {
                PerfilDAO bd = new PerfilDAO();
                bd.getLer(context);
                listId = bd.getPerfilParaLugar(listId, lugar);
            }
        } catch (Exception e) {
        }
        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            Lugar lugar = bd.getLugar(id);
            listLugar.add(lugar);
        }
        return listLugar;
    }

    public ArrayList<Pessoa> gerarListaDePessoa(Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.getListaPessoa();
    }
    public ArrayList<Pessoa> gerarListaPessoaSistema(Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.getListaPessoasSistema();
    }

    private Intent mapa(double destinolatitude, double destinolongitude) {
        Intent googleMaps = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + "" + "&daddr=" + destinolatitude + "," + destinolongitude));
        googleMaps.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
        return (googleMaps);
    }

    public Intent getMapa(double l, double lg) {
        return mapa(l, lg);
    }

    public PerfilUsuario gerarLugar(Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(context);
        return bd.getFavorito(LoginAct.getPessoa().getId());
    }

    public ArrayList<Pessoa> gerarPerfisPessoasAcompanhado(ArrayList<Pessoa> pessoaArrayList, Context context) {
        ArrayList<Pessoa> pessoaArrayList1 = new ArrayList<Pessoa>();
        try {
            for (Pessoa pessoaAcompanhada : pessoaArrayList) {
                PerfilDAO bdp = new PerfilDAO();
                bdp.getLer(context);
                pessoaAcompanhada.setPerfilAtual(bdp.getFavorito(pessoaAcompanhada.getId()));
                //////////
                PerfilDAO bdc = new PerfilDAO();
                bdc.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setComida(bdc.getPerfilComida(pessoaAcompanhada.getPerfilAtual(), pessoaAcompanhada.getId()));
                //
                PerfilDAO bde = new PerfilDAO();
                bde.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setEsporte(bde.getPerfilEsporte(pessoaAcompanhada.getPerfilAtual(), LoginAct.getPessoa().getId()));
                //
                PerfilDAO bdm = new PerfilDAO();
                bdm.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setMusica(bdm.getPerfilMusica(pessoaAcompanhada.getPerfilAtual(), LoginAct.getPessoa().getId()));
                //
                PerfilDAO bdl = new PerfilDAO();
                bdl.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setLugar(bdl.getPerfilParaLugar(pessoaAcompanhada.getPerfilAtual(), LoginAct.getPessoa().getId()));

                ///////////////
                pessoaArrayList1.add(pessoaAcompanhada);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.erroBuscarLugares, Toast.LENGTH_SHORT).show();
        }

        return pessoaArrayList1;
    }
    public Lugar getLugar(int id,Context context){
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        return bd.getLugar(id);
    }
    public void addVoto(int idPessoa,Lugar lugar,double nota,Context context){
        int idLugar = lugar.getId();
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        if(bd.verificarJaVotouNoLugar(idPessoa,idLugar)){
            bd = new UsuarioDAO();
            bd.getEscrever(context);
            bd.setNota(idPessoa,idLugar,nota);

        }
        else{
            bd = new UsuarioDAO();
            bd.getEscrever(context);
            bd.updateNota(idPessoa,idLugar,nota);
        }
        LugarDAO bdl = new LugarDAO();
        bdl.getEscrever(context);
        bdl.update(lugar);

    }
    public HashMap<Lugar,Double> getNotasPorPessoa(int id,Context context) {
        UsuarioDAO userDao = new UsuarioDAO();
        userDao.getLer(context);
        return userDao.getNotasPorPessoa(id);
    }
    public Boolean verificarJaVotou(int id,Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.verificarJaVotou(id);
    }
    public ArrayList<Pessoa> gerarListaTodasPessoa(Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.getListaTodasPesssoas();
    }
    public ArrayList<Lugar> getListaLugares(Context context){
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        return bd.getListaLugar();
    }
    public ArrayList<Lugar> atualizarNotaSlope(ArrayList<Lugar> lugarArrayList,Context context){
        ArrayList<Lugar> listLugar = new ArrayList<Lugar>();
        for (Lugar lugar : lugarArrayList) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            lugar = bd.getLugar(lugar.getId());
            //todo tem que tirar esse IF daqui.
            if (lugar.getNotaGeral() >=3.8 && listLugar.size() <= 4) {
                listLugar.add(lugar);
            }
        }
        return listLugar;

    }


    public ArrayList<Lugar> gerarLugarAcompanhado(ArrayList<Pessoa> pessoaArrayList, Context context) {
        ArrayList<Lugar> listLugar = new ArrayList<Lugar>();
        ArrayList<Integer> listId = new ArrayList<Integer>();
        try {
            for (Pessoa pessoaAcompanhada : pessoaArrayList) {
                PerfilDAO bdp = new PerfilDAO();
                bdp.getLer(context);
                pessoaAcompanhada.setPerfilAtual(bdp.getFavorito(pessoaAcompanhada.getId()));
                //////////
                PerfilDAO bdc = new PerfilDAO();
                bdc.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setComida(bdc.getPerfilComida(pessoaAcompanhada.getPerfilAtual(), pessoaAcompanhada.getId()));
                //
                PerfilDAO bde = new PerfilDAO();
                bde.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setEsporte(bde.getPerfilEsporte(pessoaAcompanhada.getPerfilAtual(), LoginAct.getPessoa().getId()));
                //
                PerfilDAO bdm = new PerfilDAO();
                bdm.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setMusica(bdm.getPerfilMusica(pessoaAcompanhada.getPerfilAtual(), LoginAct.getPessoa().getId()));
                //
                PerfilDAO bdl = new PerfilDAO();
                bdl.getLer(context);
                pessoaAcompanhada.getPerfilAtual().setLugar(bdl.getPerfilParaLugar(pessoaAcompanhada.getPerfilAtual(), LoginAct.getPessoa().getId()));

                ///////////////
                for (PerfilComida perfilComida : pessoaAcompanhada.getPerfilAtual().getComida()) {
                    PerfilDAO bd = new PerfilDAO();
                    bd.getLer(context);
                    listId = bd.getPerfilParaLugar(perfilComida);
                }
                for (PerfilMusica perfilMusica : pessoaAcompanhada.getPerfilAtual().getMusica()) {
                    PerfilDAO bd = new PerfilDAO();
                    bd.getLer(context);
                    listId = bd.getPerfilParaLugar(listId, perfilMusica);
                }
                for (PerfilEsporte perfilEsporte : pessoaAcompanhada.getPerfilAtual().getEsporte()) {
                    PerfilDAO bd = new PerfilDAO();
                    bd.getLer(context);
                    listId = bd.getPerfilParaLugar(listId, perfilEsporte);
                }
            }
        } catch (Exception e) {}

        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(context);
            Lugar lugar = bd.getLugar(id);
            listLugar.add(lugar);
        }
        return listLugar;

    }
}

