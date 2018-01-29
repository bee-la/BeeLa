package br.ufrpe.beela.perfil.negocio;

import android.content.Context;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by vidal on 14/12/2017.
 */

public class PerfilService {
    private Pessoa pessoa = LoginAct.getPessoa();
    public boolean verificarNomeIgual(String NomePerfil) {
        boolean saida = true;
        for (PerfilUsuario perfilUsuario : PerfilAct.getListaPerfis())
            if (perfilUsuario.getNome().equals(NomePerfil)) {
                saida = false;
                break;
            }
        return saida;
    }

    public boolean verificarNomeVazio(String nomePerfil) {
        if (nomePerfil.isEmpty()) {
            return false;
        }
        return true;
    }

    public void adcComida(PerfilUsuario perfilUsuario, Context context) {
        for (PerfilComida comida : perfilUsuario.getComida()) {
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            comida.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilComida(comida,pessoa.getId());
        }
    }

    public void adcMusica(PerfilUsuario perfilUsuario, Context context) {
        for (PerfilMusica musica : perfilUsuario.getMusica()) {
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            musica.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilMusica(musica,pessoa.getId());
        }
    }

    public void adcLugar(PerfilUsuario perfilUsuario, Context context) {
        for (PerfilLugar lugar : perfilUsuario.getLugar()) {
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            lugar.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilLugar(lugar,pessoa.getId());
        }
    }

    public void adcEsporte(PerfilUsuario perfilUsuario, Context context) {
        for (PerfilEsporte esporte : perfilUsuario.getEsporte()) {
            PerfilDAO bd = new PerfilDAO();
            bd.getEscrever(context);
            esporte.setNome_perfil(perfilUsuario.getNome());
            bd.inserirPerfilEsporte(esporte,pessoa.getId());
        }
    }

    public void adcPerfil(PerfilUsuario perfilUsuario, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.inserirPerfil(perfilUsuario,pessoa.getId());
    }

    public void adcPerfilFavorito(int id, String nome, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.inserirPerfilFavorito(id, nome);
    }
    public void adcPerfilFavoritoAtual(int id, String nome, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.updatePerfilAtual(nome,id);
    }

    public void excluirDoBanco(int Id, String nomePerfil, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getEscrever(context);
        bd.deletePerfilUsuario(Id, nomePerfil);

    }

    public ArrayList<PerfilUsuario> montarPerfis(Pessoa pessoa, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(context);
        return bd.getPerfil(pessoa.getId());

    }

    public void adcListaComida(ArrayList<CheckBox> checkBoxesComidas, ArrayList<PerfilComida> listaComida, PerfilUsuario perfilUsuario) {
        for (CheckBox x : checkBoxesComidas) {
            if (x.isChecked()) {
                PerfilComida comida = new PerfilComida();
                comida.setNome((String) x.getText());
                listaComida.add(comida);
            }
        }
        perfilUsuario.setComida(listaComida);
    }

    public void adcListaEsporte(ArrayList<CheckBox> checkBoxesEsportes, ArrayList<PerfilEsporte> listaEsporte, PerfilUsuario perfilUsuario) {
        for (CheckBox x : checkBoxesEsportes) {
            if (x.isChecked()) {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setNome((String) x.getText());
                listaEsporte.add(esporte);
            }
        }
        perfilUsuario.setEsporte(listaEsporte);
    }

    public void adcListaMusica(ArrayList<CheckBox> checkBoxesMusicas, ArrayList<PerfilMusica> listaMusica, PerfilUsuario perfilUsuario) {
        for (CheckBox x : checkBoxesMusicas) {
            if (x.isChecked()) {
                PerfilMusica musica = new PerfilMusica();
                musica.setNome(x.getText().toString());
                listaMusica.add(musica);
            }
        }
        perfilUsuario.setMusica(listaMusica);
    }

    public void adcListaLugares(ArrayList<CheckBox> checkBoxesLugares, ArrayList<PerfilLugar> listaLugar, PerfilUsuario perfilUsuario) {
        for (CheckBox x : checkBoxesLugares) {
            if (x.isChecked()) {
                PerfilLugar lugar = new PerfilLugar();
                lugar.setNome(x.getText().toString());
                listaLugar.add(lugar);
            }
        }
        perfilUsuario.setLugar(listaLugar);
    }

    public void initData(List listDataHeader, HashMap listHash) {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add(R.string.Comidas);
        listDataHeader.add(R.string.Musicas);
        listDataHeader.add(R.string.Esportes);

        List<String> comidas = new ArrayList<>();
        comidas.add("Massas");
        comidas.add("Vegetariano");
        comidas.add("Orientais");


        List<String> musicas = new ArrayList<>();
        musicas.add("Sertanejo");
        musicas.add("Rock");
        musicas.add("internacional");

        List<String> esportes = new ArrayList<>();
        esportes.add("jogo");
        esportes.add("volei");
        esportes.add("basquete");

        listHash.put(R.string.Comidas, comidas);
        listHash.put(listDataHeader.get(1), musicas);
        listHash.put(listDataHeader.get(2), esportes);

    }
}
