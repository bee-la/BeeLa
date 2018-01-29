package br.ufrpe.beela.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.lugar.gui.LugarAct;
import br.ufrpe.beela.lugar.negocio.ListViewLugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.negocio.ListViewContato;

/**
 * Created by Anderson on 20/01/2018.
 */

public class ContatoAct extends AppCompatActivity {
    private ListView listViewContatos;
    private ArrayList<Pessoa> pessoaArrayList = EscolhaProgramaAct.getListaPessoa();
    private Button botaoConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        setListView();
        clicarBotaoConfirmar();
    }

    public ArrayList<Pessoa> getContatos() {
        return pessoaArrayList;
    }

    public void setListView() {
        listViewContatos = (ListView) findViewById(R.id.listView3);
        ListViewContato lista = new ListViewContato(ContatoAct.this);
        listViewContatos.setAdapter(lista);
    }

    private void clicarBotaoConfirmar() {

        botaoConfirmar = (Button) findViewById(R.id.button5);
        botaoConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verificaSelecionados();
                irTelaLugarAct();

            }
        });
    }

    public void exibirToast(TextView texto) {
        Toast.makeText(getApplicationContext(), texto.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    public void irTelaLugarAct(){
        startActivity(new Intent(ContatoAct.this, LugarAct.class));
    }
    private void verificaSelecionados() {
        ArrayList<Pessoa> pessoaArrayList = new ArrayList<Pessoa>();
        try {
            if (listViewContatos != null) {
                Adapter adapter = (Adapter) listViewContatos.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Pessoa pessoa = (Pessoa) adapter.getItem(i);
                    if (pessoa.isSelecionado()) {
                        pessoaArrayList.add(pessoa);
                    }
                }
            }
            EscolhaProgramaAct.setListaLugar(gerarLugarAcompanhado(pessoaArrayList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Lugar> gerarLugarAcompanhado(ArrayList<Pessoa> pessoaArrayList) {
        ArrayList<Lugar> listLugar = new ArrayList<Lugar>();
        ArrayList<Integer> listId = new ArrayList<Integer>();
        try {
            for (Pessoa pessoa : pessoaArrayList) {
                for (PerfilComida perfilComida : pessoa.getPerfilAtual().getComida()) {
                    PerfilDAO bd = new PerfilDAO();
                    bd.getLer(this);
                    listId = bd.getPerfilParaLugar(perfilComida);
                }
                for (PerfilMusica perfilMusica : pessoa.getPerfilAtual().getMusica()) {
                    PerfilDAO bd = new PerfilDAO();
                    bd.getLer(this);
                    listId = bd.getPerfilParaLugar(listId, perfilMusica);
                }
                for (PerfilEsporte perfilEsporte : pessoa.getPerfilAtual().getEsporte()) {
                    PerfilDAO bd = new PerfilDAO();
                    bd.getLer(this);
                    listId = bd.getPerfilParaLugar(listId, perfilEsporte);
                }
            }
        } catch (Exception e) {}

        for (int id : listId) {
            LugarDAO bd = new LugarDAO();
            bd.getLer(this);
            Lugar lugar = bd.getLugar(id);
            listLugar.add(lugar);
        }
        return listLugar;


        //TODO     Função que encontra os lugares dos contatos selecionados
    }
}
