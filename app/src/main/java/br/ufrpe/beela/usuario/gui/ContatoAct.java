package br.ufrpe.beela.usuario.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.database.negocio.BancoDeDados;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.lugar.negocio.ListViewLugar;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.negocio.ListViewContato;

/**
 * Created by Anderson on 20/01/2018.
 */

public class ContatoAct extends AppCompatActivity {
    private BancoDeDados bancoDeDados=new BancoDeDados();
    private ListView listViewContatos;
    private ArrayList<Pessoa> pessoaArrayList = EscolhaProgramaAct.getListaPessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        setListView();
    }

    public ArrayList<Pessoa> getContatos(){
        return pessoaArrayList;//bancoDeDados.getListaContatos(this);
    }

    public void setListView(){
        listViewContatos = (ListView) findViewById(R.id.listView3);
        ListViewContato lista = new ListViewContato(ContatoAct.this);
        listViewContatos.setAdapter(lista);
    }

    public void exibirToast(TextView texto){
        Toast.makeText(getApplicationContext(), texto.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
